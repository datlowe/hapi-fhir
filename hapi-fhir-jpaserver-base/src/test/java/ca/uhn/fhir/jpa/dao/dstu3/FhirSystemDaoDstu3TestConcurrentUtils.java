package ca.uhn.fhir.jpa.dao.dstu3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;
import org.hl7.fhir.dstu3.model.Meta;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Assert;

import ca.uhn.fhir.jpa.dao.IFhirSystemDao;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;

public class FhirSystemDaoDstu3TestConcurrentUtils {
	
	private final IFhirSystemDao<Bundle, Meta> mySystemDao;
	private final ServletRequestDetails mySrd;
	
	private final List<Exception> thrown = Collections.synchronizedList(new ArrayList<>());

	public FhirSystemDaoDstu3TestConcurrentUtils(
			IFhirSystemDao<Bundle, Meta> mySystemDao,
			ServletRequestDetails mySrd) 
	{
		this.mySystemDao = mySystemDao;
		this.mySrd = mySrd;
	}
	
	private void submitPatient(String idSuffix) {
		Bundle bundle = new Bundle();
		bundle.setType(BundleType.TRANSACTION);

		Patient patient = new Patient();
		patient.setId(IdDt.newRandomUuid()); //will be changed by the server to "dedek", see bellow  
		patient.addName().setText("Jan Dědek");

		bundle.addEntry()
		    .setFullUrl(patient.getId())
		    .setResource(patient)
		    .getRequest()
		        .setMethod(HTTPVerb.PUT) // PUT = update, but it will create if there is none  
		        .setUrl("Patient/dedek"+idSuffix); //This is the new id of the patient

		try {
			Bundle resp = mySystemDao.transaction(mySrd, bundle);
			Assert.assertEquals("201 Created", resp.getEntry().get(0).getResponse().getStatus());
		} catch (Exception e) {
			thrown.add(e);
		}
	}
	
	
	private Thread createThread(final int threadNum) {
		return 	new Thread(new Runnable() {
			int patNum = 0;
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					submitPatient(""+threadNum+"n"+ ++patNum);
				}
			}
		});
	}
	
	public void doTest() {
		Thread t[] = new Thread[5];
		for (int i = 0; i < t.length; i++) {
			t[i] = createThread(i);
			t[i].start();
		}
		
		for (int i = 0; i < t.length; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		
		if (! thrown.isEmpty()) {
			throw new IllegalStateException(thrown.size() + " exceptions detected, see the first one in cause of this...", thrown.get(0)); 
		}
	}
}
