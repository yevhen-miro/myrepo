package de.hydro.gv.orgpm.models;

import java.io.Serializable;

import de.hydro.gv.orgpm.auth.RolleEnum;

public class RolleModel extends Model<RolleEnum, RolleModel> implements Serializable {

	private static final long serialVersionUID = -3392669896785233915L;

	private RolleEnum name;

	public RolleEnum getName() {
		return this.name;
	}

	public void setName( RolleEnum name ) {
		this.name = name;
	}

	public RolleModel( RolleEnum rolle ) {
		super( rolle );
	}

	@Override
	public RolleModel copyToModel() {
		this.setName( this.entity );
		return null;
	}

	@Override
	public RolleEnum copyToEntity() {
		this.entity.name();
		return null;
	}

}
