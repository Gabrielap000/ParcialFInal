/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mg.paez
 */
@RunWith(Arquillian.class)
public class PokemonPersistenceTest 
{
    
    @Inject
    private PokemonPersistence pokemonPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PokemonEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    private void clearData()
    {
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }


    @Test
    public void createPokemonTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        PokemonEntity result = pokemonPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(),entity.getId());
        
    }


    @Test
    public void getPokemonsTest() 
    {
        List<PokemonEntity> list = pokemonPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PokemonEntity ent : list) 
        {
            boolean found = false;
            for (PokemonEntity entity : data) 
            {
                if (ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getPokemonTest() 
    {
        PokemonEntity entity = data.get(0);
        PokemonEntity newEntity = pokemonPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(),entity.getId());
    }

    @Test
    public void updatePokemonTest() 
    {
        PokemonEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);

        newEntity.setId(entity.getId());

        pokemonPersistence.update(newEntity);

        PokemonEntity resp = em.find(PokemonEntity.class, entity.getId());

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(),entity.getId());
    }


    @Test
    public void deletePokemonTest() 
    {
        PokemonEntity entity = data.get(0);
        pokemonPersistence.delete(entity.getId());
        PokemonEntity deleted = em.find(PokemonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
