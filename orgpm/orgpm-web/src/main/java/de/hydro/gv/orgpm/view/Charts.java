package de.hydro.gv.orgpm.view;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import de.hydro.gv.orgpm.actions.SecurityActions;
import de.hydro.gv.orgpm.services.BuchungService;
import de.hydro.gv.orgpm.util.Logged;

@ManagedBean
@ViewScoped
@Logged
public class Charts implements Serializable {

	private static final long serialVersionUID = 1322231899089156979L;

	@Inject
	private BuchungService buchungService;

	@Inject
	private SecurityActions securityActions;

	private LineChartModel lineModel;

	private PieChartModel pieModel;

	private BarChartModel barModel;

	private Date filterMonth;

	private Integer monthNum = 10;

	public Integer getMonthNum() {
		return this.monthNum;
	}

	public void setMonthNum( Integer monthNum ) {
		this.monthNum = monthNum;
	}

	public Date getFilterMonth() {
		return this.filterMonth;
	}

	public void setFilterMonth( Date filterMonth ) {
		this.filterMonth = filterMonth;
	}

	public BarChartModel getBarModel() {
		return this.barModel;
	}

	public LineChartModel getLineChartModel() {
		return this.lineModel;
	}

	public PieChartModel getPieModel() {
		return this.pieModel;
	}

	@PostConstruct
	private void initModel() throws Exception {

		this.buildLineChart();
		this.buildBarChart();
		this.buildPieChart();
		this.getMonthAndYear();
	}

	private void buildLineChart() throws Exception {
		this.lineModel = new LineChartModel();
		this.lineModel.setTitle( "Meine Buchungen" );
		this.lineModel.setZoom( true );
		this.lineModel.getAxis( AxisType.Y ).setLabel( "Std." );

		DateAxis axis = new DateAxis( "Datum" );
		axis.setTickAngle( -50 );
		axis.setMax( "2015-12-31" );
		axis.setTickFormat( "%d.%m.%y" );

		this.lineModel.getAxes().put( AxisType.X, axis );

		ChartSeries buchungen = new ChartSeries();
		DateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
		Map<Object, Number> buchungMap = new HashMap<Object, Number>();
		for ( Object[] b : this.buchungService.getDauerByMitarbeiter( this.securityActions
				.getSecurityPrincipalForLoggedInUser().toUpperCase() ) ) {
			buchungMap.put( df.format( b[0] ), (Long) b[1] );
		}
		Map<Object, Number> sortedMap = new TreeMap<Object, Number>( buchungMap );

		buchungen.setData( sortedMap );
		this.lineModel.addSeries( buchungen );
	}

	private void buildBarChart() throws Exception {
		this.barModel = new BarChartModel();
		this.barModel.setTitle( "Meine Buchungen" );
		this.barModel.getAxis( AxisType.Y ).setLabel( "Std." );
		this.barModel.getAxis( AxisType.X ).setLabel( "Datum" );
		this.barModel.getAxis( AxisType.X ).setTickAngle( -90 );
		this.barModel.getAxis( AxisType.X ).setTickFormat( "%d.%m.%y" );
		this.barModel.setShowPointLabels( true );

		DateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
		List<String> monthDays = new ArrayList<String>();

		Calendar cal = Calendar.getInstance();
		if( this.filterMonth == null ) {
			cal.setTime( new Date() );
		} else {
			cal.setTime( this.filterMonth );
		}

		cal.set( Calendar.DAY_OF_MONTH, 1 );
		int myMonth = cal.get( Calendar.MONTH );
		if( this.monthNum == null ) {
			this.monthNum = myMonth;
		}

		while ( myMonth == cal.get( Calendar.MONTH ) ) {
			monthDays.add( df.format( cal.getTime() ) );
			cal.add( Calendar.DAY_OF_MONTH, 1 );
		}

		ChartSeries buchungen = new ChartSeries();
		Map<Object, Number> buchungMap = new HashMap<Object, Number>();
		for ( String day : monthDays ) {
			if( this.buchungService.getDauerByMitarbeiterAndMonth(
					this.securityActions.getSecurityPrincipalForLoggedInUser().toUpperCase(), this.monthNum ).size() == 0 ) {
				buchungMap.put( day, 0 );
			}
			for ( Object[] b : this.buchungService.getDauerByMitarbeiterAndMonth( this.securityActions
					.getSecurityPrincipalForLoggedInUser().toUpperCase(), this.monthNum ) ) {
				String bDay = df.format( b[0] );
				if( bDay.compareTo( day ) == 0 || bDay == day || bDay.equals( day ) ) {
					buchungMap.put( df.format( b[0] ), (Long) b[1] );
				} else if( !buchungMap.containsKey( day ) ) {
					buchungMap.put( day, 0 );
				}
			}

		}
		Map<Object, Number> sortedMap = new TreeMap<Object, Number>( buchungMap );

		buchungen.setData( sortedMap );
		this.barModel.addSeries( buchungen );
	}

	private void buildPieChart() throws Exception {
		this.pieModel = new PieChartModel();
		this.pieModel.setTitle( "Meine Buchungen nach Projekt" );
		this.pieModel.setLegendPosition( "e" );
		this.pieModel.setShowDataLabels( true );
		this.pieModel.setDiameter( 150 );

		Map<String, Number> buchungMap = new HashMap<String, Number>();
		if( this.buchungService.getDauerByProjektUndMitarbeiterAndMonth(
				this.securityActions.getSecurityPrincipalForLoggedInUser().toUpperCase(), this.monthNum ).size() == 0 ) {
			buchungMap.put( "Keine Buchungen gefunden", 0 );
		}
		for ( Object[] b : this.buchungService.getDauerByProjektUndMitarbeiterAndMonth( this.securityActions
				.getSecurityPrincipalForLoggedInUser().toUpperCase(), this.monthNum ) ) {
			buchungMap.put( (String) b[0], (Long) b[1] );
		}

		this.pieModel.setData( buchungMap );
	}

	public void onMonthSelect( SelectEvent event ) throws Exception {
		Date filterDate = (Date) event.getObject();
		Calendar cal = Calendar.getInstance();
		cal.setTime( filterDate );
		this.monthNum = cal.get( Calendar.MONTH ) + 1;
		this.initModel();
		;
	}

	public void decrementMonth() throws Exception {
		Calendar c = Calendar.getInstance();
		if( this.filterMonth == null ) {
			this.filterMonth = new Date();
		}
		c.setTime( this.filterMonth );

		c.add( Calendar.MONTH, -1 );
		this.filterMonth.setTime( c.getTimeInMillis() );
		this.monthNum = c.get( Calendar.MONTH );
		this.monthNum = this.monthNum + 1;
		this.initModel();
		// ... business logic to update date dependent data
	}

	public void incrementMonth() throws Exception {
		Calendar c = Calendar.getInstance();
		if( this.filterMonth == null ) {
			this.filterMonth = new Date();
		}
		c.setTime( this.filterMonth );
		c.add( Calendar.MONTH, 1 );
		this.filterMonth.setTime( c.getTimeInMillis() );
		this.monthNum = c.get( Calendar.MONTH );
		this.monthNum = this.monthNum + 1;
		this.initModel();
		// ... business logic to update date dependent data
	}

	public String getMonthAndYear() {
		Calendar c = Calendar.getInstance();
		String month = "wrong_month";
		String year = "wrong_year";
		String output;
		if( this.filterMonth == null ) {
			c.setTime( new Date() );
			c.get( Calendar.MONTH );
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getMonths();
			if( c.get( Calendar.MONTH ) >= 0 && c.get( Calendar.MONTH ) <= 11 ) {
				month = months[c.get( Calendar.MONTH )];
			}
			StringBuilder sb = new StringBuilder();
			year = sb.append( c.get( Calendar.YEAR ) ).toString();
			output = month + " " + year;
		} else {
			c.setTime( this.filterMonth );
			c.get( Calendar.MONTH );
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getMonths();
			if( c.get( Calendar.MONTH ) >= 0 && c.get( Calendar.MONTH ) <= 11 ) {
				month = months[c.get( Calendar.MONTH )];
			}
			StringBuilder sb = new StringBuilder();
			year = sb.append( c.get( Calendar.YEAR ) ).toString();
			output = month + " " + year;
		}
		return output;
	}
}