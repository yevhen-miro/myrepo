package de.hydro.gv.orgpm.models;

import java.io.Serializable;

public abstract class Model<E, M extends Model> implements Serializable {

	private static final long serialVersionUID = -8681455389321667530L;

	protected E entity;
	protected M model;

	public Model() {
	}

	public Model( E entity ) {
		this.entity = entity;
		this.copyToModel( );
	}

	public abstract M copyToModel( );

	public abstract E copyToEntity( );

	@SuppressWarnings( "unchecked" )
	public E convertToEntity() {
		entity = this.copyToEntity( );
		return entity;
	}

	@SuppressWarnings( "unchecked" )
	public M convertToModel( ) {
		model = copyToModel( );
		return model;

	}

}
