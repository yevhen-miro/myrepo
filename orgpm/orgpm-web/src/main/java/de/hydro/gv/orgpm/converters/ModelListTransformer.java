//package de.hydro.gv.orgpm.converters;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import javax.enterprise.context.RequestScoped;
//
//import de.hydro.gv.orgpm.models.Model;
//
//@RequestScoped
//public class ModelListTransformer {
//
//	@SuppressWarnings( "unchecked" )
//	public <E, M extends Model> Collection<M> transform( Collection<E> entities, Class<M> destinationClazz ) {
//		M model = null;
//		ArrayList<M> liste = new ArrayList<M>();
//		for ( E entity : entities ) {
//			try {
//				model = destinationClazz.newInstance();
//			} catch ( InstantiationException | IllegalAccessException e ) {
//				e.printStackTrace();
//			}
//			model = (M) model.convertToModel();
//			liste.add( model );
//		}
//		return liste;
//	}
//
// }
