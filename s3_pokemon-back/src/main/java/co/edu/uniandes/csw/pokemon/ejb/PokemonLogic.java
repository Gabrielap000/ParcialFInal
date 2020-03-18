/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.ejb;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import javax.inject.Inject;

/**
 *
 * @author mg.paez
 */
public class PokemonLogic 
{
     @Inject
    private PokemonPersistence persistence; 
    
    public PokemonEntity createPokemon(PokemonEntity pokemon) throws BusinessLogicException 
   {
       if ((pokemon.getDebilidad().equals(pokemon.getTipo())))
       {
           throw new BusinessLogicException("No se puede crear un pokemon que su tipo sea su debilidad."); 
       } 
       if ((persistence.findByName(pokemon.getNombre())!= null))
       {
           throw new BusinessLogicException("Este nombre ya existe. EL pokemon no se puede llamar asi."); 
       }
       if (pokemon.getAtaques().size() < 1)
       {
           throw new BusinessLogicException("No se puede crear el pokemon por que necesita tener minimo un ataque."); 
       }
       
       for(int i = 0; i < pokemon.getAtaques().size(); i++)
        {
            if ((pokemon.getAtaques().get(i).getDano() < 10))
                 throw new BusinessLogicException("No se puede crear el ataque por que tiene que ser mayor a 10."); 
             if ((pokemon.getAtaques().get(i).getDano() > 100))
                 throw new BusinessLogicException("No se puede crear el ataque por que tiene que ser menor a 100.");
        }       
       pokemon = persistence.create(pokemon); 
       return pokemon; 
   }
}
