















package ca.uhn.fhir.model.dstu.composite;

import java.util.List;

import ca.uhn.fhir.model.api.BaseElement;
import ca.uhn.fhir.model.api.ICompositeDatatype;
import ca.uhn.fhir.model.api.IElement;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.DatatypeDef;
import ca.uhn.fhir.model.api.annotation.Description;

/**
 * HAPI/FHIR <b>Range</b> Datatype
 * (Set of values bounded by low and high)
 *
 * <p>
 * <b>Definition:</b>
 * A set of ordered Quantities defined by a low and high limit.
 * </p> 
 *
 * <p>
 * <b>Requirements:</b>
 * Need to be able to specify ranges of values
 * </p> 
 */
@DatatypeDef(name="Range") 
public class RangeDt 
        extends  BaseElement         implements ICompositeDatatype  {

	/**
	 * Constructor
	 */
	public RangeDt() {
		// nothing
	}


	@Child(name="low", type=QuantityDt.class, order=0, min=0, max=1)	
	@Description(
		shortDefinition="Low limit",
		formalDefinition="The low limit. The boundary is inclusive."
	)
	private QuantityDt myLow;
	
	@Child(name="high", type=QuantityDt.class, order=1, min=0, max=1)	
	@Description(
		shortDefinition="High limit",
		formalDefinition="The high limit. The boundary is inclusive."
	)
	private QuantityDt myHigh;
	

	@Override
	public boolean isEmpty() {
		return super.isBaseEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(  myLow,  myHigh);
	}
	
	@Override
	public java.util.List<IElement> getAllPopulatedChildElements() {
		return getAllPopulatedChildElementsOfType(null);
	}

	@Override
	public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
		return ca.uhn.fhir.util.ElementUtil.allPopulatedChildElements(theType, myLow, myHigh);
	}

	/**
	 * Gets the value(s) for <b>low</b> (Low limit).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * The low limit. The boundary is inclusive.
     * </p> 
	 */
	public QuantityDt getLow() {  
		if (myLow == null) {
			myLow = new QuantityDt();
		}
		return myLow;
	}

	/**
	 * Sets the value(s) for <b>low</b> (Low limit)
	 *
     * <p>
     * <b>Definition:</b>
     * The low limit. The boundary is inclusive.
     * </p> 
	 */
	public RangeDt setLow(QuantityDt theValue) {
		myLow = theValue;
		return this;
	}

  
	/**
	 * Gets the value(s) for <b>high</b> (High limit).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * The high limit. The boundary is inclusive.
     * </p> 
	 */
	public QuantityDt getHigh() {  
		if (myHigh == null) {
			myHigh = new QuantityDt();
		}
		return myHigh;
	}

	/**
	 * Sets the value(s) for <b>high</b> (High limit)
	 *
     * <p>
     * <b>Definition:</b>
     * The high limit. The boundary is inclusive.
     * </p> 
	 */
	public RangeDt setHigh(QuantityDt theValue) {
		myHigh = theValue;
		return this;
	}

  



}