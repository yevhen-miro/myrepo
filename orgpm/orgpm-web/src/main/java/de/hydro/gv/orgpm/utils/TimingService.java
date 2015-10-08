package de.hydro.gv.orgpm.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;

@Stateless
public class TimingService {

	private static final Long PAUSE_VON = 700L;
	private static final Long PAUSE_BIS = 740L;
	public static final int PAUSE_V = 42000000;
	public static final int PAUSE_B = 44400000;

	public Long calculateMinutes( Date anfangZeit, Date endeZeit ) {
		Long duration = this.convertTimeToMinutes( endeZeit ) - this.convertTimeToMinutes( anfangZeit );
		return ( ( this.convertTimeToMinutes( anfangZeit ) >= PAUSE_VON || this.convertTimeToMinutes( endeZeit ) >= PAUSE_VON )
				&& ( this.convertTimeToMinutes( anfangZeit ) < PAUSE_BIS || this.convertTimeToMinutes( endeZeit ) < PAUSE_BIS ) ? duration - 40
				: duration );
	}

	public Double calculateHours( Date anfangZeit, Date endeZeit ) {
		Double duration = this.convertTimeToHours( endeZeit ) - this.convertTimeToHours( anfangZeit );
		return ( ( this.convertTimeToHours( anfangZeit ) >= PAUSE_VON || this.convertTimeToHours( endeZeit ) >= PAUSE_VON )
				&& ( this.convertTimeToHours( anfangZeit ) < PAUSE_BIS || this.convertTimeToHours( endeZeit ) < PAUSE_BIS ) ? duration - 40
				: duration );
	}

	public Boolean isPauseTime( Date anfangZeit, Date endeZeit ) {
		if( ( this.convertTimeToMinutes( anfangZeit ) >= PAUSE_VON || this.convertTimeToMinutes( endeZeit ) >= PAUSE_VON )
				&& ( this.convertTimeToMinutes( anfangZeit ) < PAUSE_BIS || this.convertTimeToMinutes( endeZeit ) < PAUSE_BIS ) ) {
			return true;
		}
		return false;
	}

	private Long convertTimeToMinutes( Date time ) {
		return TimeUnit.MILLISECONDS.toMinutes( time.getTime() );
	}

	private Double convertTimeToHours( Date time ) {
		Double t = (double) this.convertTimeToMinutes( time ) / 60;
		int temp = (int) ( t * Math.pow( 10, 2 ) );
		return ( temp ) / Math.pow( 10, 2 );
	}

	@SuppressWarnings( "unused" )
	private Double convertTimeToHoursAndMinutes( Date time ) {
		Double minutes = (double) ( this.convertTimeToMinutes( time ) % 60 ) / 100;
		Double hours = (double) TimeUnit.MINUTES.toHours( this.convertTimeToMinutes( time ) );
		Double result = hours + minutes;
		return result;
	}

}
