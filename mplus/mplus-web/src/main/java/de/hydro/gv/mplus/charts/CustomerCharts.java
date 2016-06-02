package de.hydro.gv.mplus.charts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.services.CustomerService;

@ManagedBean
@ViewScoped
public class CustomerCharts implements Serializable{

	private static final long serialVersionUID = 7396324799388546191L;
	
	@Inject
	CustomerService customerService;

	private BarChartModel barModel;
	
	private Customer customer;
	
	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Integer [] monthNumbers = {1,2,3,4,5,6,7,8,9,10,11,12};
	
	   @PostConstruct
	    public void init() {
		   this.buildBarChart();
	    }

	private void buildBarChart() {
		customer = customerService.findCustomerById(11L);
		this.barModel = new BarChartModel();
		this.barModel.setTitle( "Customer Performance" );
		this.barModel.getAxis( AxisType.Y ).setLabel( "Kg." );
		this.barModel.getAxis( AxisType.X ).setLabel( "Month" );
		this.barModel.getAxis( AxisType.X ).setTickAngle( -90 );
		this.barModel.getAxis( AxisType.X ).setTickFormat( "%m.%y" );
		this.barModel.setShowPointLabels( true );

		ChartSeries performance = new ChartSeries();
		Map<Object, Number> performanceMap = new HashMap<Object, Number>();
		
		//for ( Integer m : monthNumbers ) {
		
		List<Object[]> o = this.customerService.findCustomerTonnageByCustomer(customer.getCustomer_id());
		System.out.println(o.size());

			for ( Object[] o1 : this.customerService.findCustomerTonnageByCustomerAndMonth(customer.getCustomer_id(), 1)) { 
				
					performanceMap.put( o1[0], (Number) o1[1] );
				}
			//}

		
		
		Map<Object, Number> sortedMap = new TreeMap<Object, Number>( performanceMap );

		performance.setData( sortedMap );
		this.barModel.addSeries( performance );
	}
	 
}
