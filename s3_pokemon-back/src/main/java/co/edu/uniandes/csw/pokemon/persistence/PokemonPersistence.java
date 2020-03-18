/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mg.paez
 */
@Stateless
public class PokemonPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PokemonPersistence.class.getName());

    @PersistenceContext(unitName = "pokemonPU")
    
    protected EntityManager em;
    
    public PokemonEntity create(PokemonEntity pokemon) 
    {
        LOGGER.log(Level.INFO, "Creando un nuevo pokemon");
        em.persist(pokemon);
        LOGGER.log(Level.INFO, "Pokemon creado");
    
        return pokemon; 
    }
     
    public PokemonEntity find(long pId)
    {
       LOGGER.log(Level.INFO, "Consultando el libro con id={0}", pId);
        return em.find(PokemonEntity.class, pId);
    }
      
      public List<PokemonEntity> findAll() 
    {
        LOGGER.log(Level.INFO, "Consultando todos los pokemons");
        TypedQuery query = em.createQuery("select u from PokemonEntity u", PokemonEntity.class);
        return query.getResultList();
    }
      
    public PokemonEntity findByName(String nombre) 
    {
        LOGGER.log(Level.INFO, "Consultando el pokemon por nombre ", nombre);
        TypedQuery query = em.createQuery("Select e From PokemonEntity e where e.name = :name", PokemonEntity.class);
        query = query.setParameter("nombre", nombre);
        List<PokemonEntity> mismoNombre = query.getResultList();
        PokemonEntity result;
        if (mismoNombre == null) 
        {
            result = null;
        } 
        else if (mismoNombre.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = mismoNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar pokemon por nombre ", nombre);
        return result;
    }
   
   public PokemonEntity update(PokemonEntity pokemon)
   {
      LOGGER.log(Level.INFO, "Actualizando el author con id={0}", pokemon.getId());
       return em.merge(pokemon);
   }
   
   public void delete(long pId)
   {
       LOGGER.log(Level.INFO, "Borrando el author con id={0}", pId);
       PokemonEntity pokemon = em.find(PokemonEntity.class, pId);
       em.remove(pokemon);
   }
    
}
