/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.resources;

import co.edu.uniandes.csw.pokemon.dtos.PokemonDTO;
import co.edu.uniandes.csw.pokemon.ejb.PokemonLogic;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ash Ketchum
 */

@Path("/pokemons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PokemonResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(PokemonResource.class.getName());

     @Inject
    private PokemonLogic pokemonLogic;
  
  @POST
    public PokemonDTO createAuthor(PokemonDTO pokemon) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "PokemonResource createPokemon: input: {0}", pokemon);
        PokemonDTO pokemonDTO = new PokemonDTO(pokemonLogic.createPokemon(pokemon.toEntity()));
        LOGGER.log(Level.INFO, "PokemonResource createPokemon: output: {0}", pokemonDTO);
        return pokemonDTO;
    }
    
}
