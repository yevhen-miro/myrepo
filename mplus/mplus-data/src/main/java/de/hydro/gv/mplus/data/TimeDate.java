package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "dbo.Time_Dates")
@NamedQueries( {
	@NamedQuery( name = "date.find.by.date", query = "SELECT d FROM TimeDate AS d WHERE d.dateFull = :date" ),
	@NamedQuery( name = "date.find.by.id", query = "SELECT d FROM TimeDate AS d WHERE d.dateId = :id" ),
	@NamedQuery( name = "date.find.by.timedate", query = "SELECT d.dateFull FROM TimeDate AS d WHERE d = :timedate" )} )
public class TimeDate implements Serializable{
	
	private static final long serialVersionUID = 6040210809707647757L;

	@Id
	@Column( name = "Date_Id" )
	private Long dateId;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "Date_Full" )
	private Date dateFull;
	
	@Column( name = "Date_Year" )
	private Integer year;
	
	@Column( name = "Date_Quarter" )
	private Integer quarter;
	
	@Column( name = "Date_QuarterName" )
	private String quarterName;
	
	@Column( name = "Date_Month" )
	private Integer month;
	
	@Column( name = "Date_MonthName" )
	private String monthName;
	
	@Column( name = "Date_Week" )
	private Integer week;
	
	@Column( name = "Date_WeekName" )
	private String weekName;
	
	@Column( name = "Date_WeekYear" )
	private Integer weekYear;
	
	@Column( name = "Date_DayOfMonth" )
	private Integer dayOfMonth;
	
	@Column( name = "Date_DayOfWeek" )
	private Integer dayOfWeek;
	
	@Column( name = "Date_DayName" )
	private String dayName;
	
	@Column( name = "Year_MDXValue" )
	private String yearMdxValue;
	
	@Column( name = "Quarter_MDXValue" )
	private String quarterMdxValue;
	
	@Column( name = "Month_MDXValue" )
	private String monthMdxValue;

	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public Date getDateFull() {
		return dateFull;
	}

	public void setDateFull(Date dateFull) {
		this.dateFull = dateFull;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public String getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public Integer getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(Integer weekYear) {
		this.weekYear = weekYear;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getYearMdxValue() {
		return yearMdxValue;
	}

	public void setYearMdxValue(String yearMdxValue) {
		this.yearMdxValue = yearMdxValue;
	}

	public String getQuarterMdxValue() {
		return quarterMdxValue;
	}

	public void setQuarterMdxValue(String quarterMdxValue) {
		this.quarterMdxValue = quarterMdxValue;
	}

	public String getMonthMdxValue() {
		return monthMdxValue;
	}

	public void setMonthMdxValue(String monthMdxValue) {
		this.monthMdxValue = monthMdxValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateId == null) ? 0 : dateId.hashCode());
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
		TimeDate other = (TimeDate) obj;
		if (dateId == null) {
			if (other.dateId != null)
				return false;
		} else if (!dateId.equals(other.dateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Date [dateFull=" + dateFull + "]";
	}




}
