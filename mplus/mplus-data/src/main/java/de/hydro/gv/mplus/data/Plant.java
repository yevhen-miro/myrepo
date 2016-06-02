package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="Organization_Plants",catalog="MARGIN_PLUS", schema="DBO")
@NamedQueries( {
	@NamedQuery( name = "plants.find.all", query = "SELECT p FROM Plant AS p" ) } )
public class Plant implements Serializable{

	private static final long serialVersionUID = 1231347822318739092L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "Plant_Id" )
	private Long plantId ;
	
	@Column( name = "Plant_Name" )
	private String PlantName ;
	
	@Column( name = "Ctx_Id" )
	private Long ctxId ;
	
	@Column(name = "Plant_MDXValue" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String plantMDXValue ;
	
	@Column( name = "Plant_Enabled" )
	private Boolean plantEnabled ;
	
	@Column( name = "Plant_WideMinWidth" )
	private BigDecimal plantWideMinWidth ;
	
	@Column( name = "Plant_IsAllowed" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private Boolean plantIsAllowed ;
	
	@Column( name = "Plant_BudgetYear" )
	private Integer plantBudgetYear ;
	
	@Column( name = "GrossCostChange" )
	private BigDecimal grossCostChange ;
	
	@Column( name = "Plant_Created" )
	private Date plantCreated ;
	
	@Column( name = "Plant_CreatedBy" )
	private String plantCreatedBy ;
	
	@Column( name = "Plant_Updated" )
	private Date plantUpdated ;
	
	@Column( name = "Plant_UpdatedBy" )
	private String plantUpdatedBy ;
	
	@Column( name = "Plant_CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String plantCreatedByName ;
	
	@Column( name = "Plant_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String plantUpdatedByName;
	
	@Column( name = "SAP_Plant" )
	private Integer sapPlant ;
	
	@Column( name = "SAP_UnitCodes" )
	private String sapUnitCodes ;
	
	@Column( name = "PlantMgrId" )
	private String plantMgrId ;
	
	@Column( name = "FinancialMgrId" )
	private String financialMgrId ;
	
	@Column( name = "ProductionMgrId" )
	private String productionMgrId ;

	public Long getPlantId() {
		return plantId;
	}

	public void setPlantId(Long plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return PlantName;
	}

	public void setPlantName(String plantName) {
		PlantName = plantName;
	}

	public Long getCtxId() {
		return ctxId;
	}

	public void setCtxId(Long ctxId) {
		this.ctxId = ctxId;
	}

	public String getPlantMDXValue() {
		return plantMDXValue;
	}

	public void setPlantMDXValue(String plantMDXValue) {
		this.plantMDXValue = plantMDXValue;
	}

	public Boolean getPlantEnabled() {
		return plantEnabled;
	}

	public void setPlantEnabled(Boolean plantEnabled) {
		this.plantEnabled = plantEnabled;
	}

	public BigDecimal getPlantWideMinWidth() {
		return plantWideMinWidth;
	}

	public void setPlantWideMinWidth(BigDecimal plantWideMinWidth) {
		this.plantWideMinWidth = plantWideMinWidth;
	}

	public Boolean getPlantIsAllowed() {
		return plantIsAllowed;
	}

	public void setPlantIsAllowed(Boolean plantIsAllowed) {
		this.plantIsAllowed = plantIsAllowed;
	}

	public Integer getPlantBudgetYear() {
		return plantBudgetYear;
	}

	public void setPlantBudgetYear(Integer plantBudgetYear) {
		this.plantBudgetYear = plantBudgetYear;
	}

	public BigDecimal getGrossCostChange() {
		return grossCostChange;
	}

	public void setGrossCostChange(BigDecimal grossCostChange) {
		this.grossCostChange = grossCostChange;
	}

	public Date getPlantCreated() {
		return plantCreated;
	}

	public void setPlantCreated(Date plantCreated) {
		this.plantCreated = plantCreated;
	}

	public String getPlantCreatedBy() {
		return plantCreatedBy;
	}

	public void setPlantCreatedBy(String plantCreatedBy) {
		this.plantCreatedBy = plantCreatedBy;
	}

	public Date getPlantUpdated() {
		return plantUpdated;
	}

	public void setPlantUpdated(Date plantUpdated) {
		this.plantUpdated = plantUpdated;
	}

	public String getPlantUpdatedBy() {
		return plantUpdatedBy;
	}

	public void setPlantUpdatedBy(String plantUpdatedBy) {
		this.plantUpdatedBy = plantUpdatedBy;
	}

	public String getPlantCreatedByName() {
		return plantCreatedByName;
	}

	public void setPlantCreatedByName(String plantCreatedByName) {
		this.plantCreatedByName = plantCreatedByName;
	}

	public String getPlantUpdatedByName() {
		return plantUpdatedByName;
	}

	public void setPlantUpdatedByName(String plantUpdatedByName) {
		this.plantUpdatedByName = plantUpdatedByName;
	}

	public Integer getSapPlant() {
		return sapPlant;
	}

	public void setSapPlant(Integer sapPlant) {
		this.sapPlant = sapPlant;
	}

	public String getSapUnitCodes() {
		return sapUnitCodes;
	}

	public void setSapUnitCodes(String sapUnitCodes) {
		this.sapUnitCodes = sapUnitCodes;
	}

	public String getPlantMgrId() {
		return plantMgrId;
	}

	public void setPlantMgrId(String plantMgrId) {
		this.plantMgrId = plantMgrId;
	}

	public String getFinancialMgrId() {
		return financialMgrId;
	}

	public void setFinancialMgrId(String financialMgrId) {
		this.financialMgrId = financialMgrId;
	}

	public String getProductionMgrId() {
		return productionMgrId;
	}

	public void setProductionMgrId(String productionMgrId) {
		this.productionMgrId = productionMgrId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plantId == null) ? 0 : plantId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		if (plantId == null) {
			if (other.plantId != null)
				return false;
		} else if (!plantId.equals(other.plantId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.PlantName;
	}

	
	
}
