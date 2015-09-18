package de.hydro.gv.orgpm.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import de.hydro.gv.orgpm.actions.BuchungActions;

@ManagedBean
public class Charts implements Serializable {

	private static final long serialVersionUID = 1322231899089156979L;

	@Inject
	BuchungActions buchungActions;

	private LineChartModel lineModel;

	private LineChartModel dateModel;

	private PieChartModel pieModel;

	public Charts() {
		this.createCartesianModel();
		this.createPieModel();
		this.createDateModel();
	}

	public LineChartModel getLineChartModel() {
		return this.lineModel;
	}

	public LineChartModel getDateModel() {
		return this.dateModel;
	}

	public PieChartModel getPieModel() {
		return this.pieModel;
	}

	private void createCartesianModel() {
		this.lineModel = new LineChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel( "Dauer" );

		boys.set( 2004, 120 );
		boys.set( 2005, 100 );
		boys.set( 2006, 44 );
		boys.set( 2007, 150 );
		boys.set( 2008, 25 );

		this.lineModel.addSeries( boys );
	}

	private void createDateModel() {
		// this.dateModel = new LineChartModel();
		//
		// LineChartSeries dauer = new LineChartSeries();
		// dauer.setLabel( "Dauer" );
		//
		// dauer.set(
		// this.buchungActions.getDurationByMitarbeiter().containsKey( datum ),
		// this.buchungActions
		// .getDurationByMitarbeiter().containsValue( value ) );

		this.dateModel = new LineChartModel();

		LineChartSeries dauer = new LineChartSeries();
		dauer.setLabel( "Dauer" );

		dauer.set( "2014-01-01", 51 );
		dauer.set( "2014-01-06", 22 );
		dauer.set( "2014-01-12", 65 );

		this.dateModel.addSeries( dauer );

		this.dateModel.setTitle( "Zoom for Details" );
		this.dateModel.setZoom( true );
		this.dateModel.getAxis( AxisType.Y ).setLabel( "Values" );
		DateAxis axis = new DateAxis( "Dates" );
		axis.setTickAngle( -50 );
		axis.setMax( "2014-02-01" );
		axis.setTickFormat( "%b %#d, %y" );

		this.dateModel.getAxes().put( AxisType.X, axis );
	}

	private void createPieModel() {
		this.pieModel = new PieChartModel();

		this.pieModel.set( "Brand 1", 540 );
		this.pieModel.set( "Brand 2", 325 );
		this.pieModel.set( "Brand 3", 702 );
		this.pieModel.set( "Brand 4", 421 );
	}
}