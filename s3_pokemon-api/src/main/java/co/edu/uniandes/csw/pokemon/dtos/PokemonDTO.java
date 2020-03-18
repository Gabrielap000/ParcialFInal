/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.dtos;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import co.edu.uniandes.csw.pokemon.entities.AttackEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author mg.paez
 */
public class PokemonDTO implements Serializable
{
    private String nombre;
    
    private String descripcion;
    
    private double peso;
    
    private double altura; 
    
    private PokemonType tipo; 
    
    private PokemonType debilidad; 
    
    private long id; 
    
    private List<AttackEntity> ataques = new ArrayList<>();
    
    public PokemonDTO()
    {
                
    }
    
     public PokemonDTO(PokemonEntity pokemonEntity)
    {
       if (pokemonEntity != null)
         {
            this.nombre = pokemonEntity.getNombre();
            this.descripcion = pokemonEntity.getDescripcion();
            this.peso = pokemonEntity.getPeso();
            this.altura = pokemonEntity.getAltura();
            this.tipo = pokemonEntity.getTipo();
            this.debilidad = pokemonEntity.getDebilidad();
            this.ataques = pokemonEntity.getAtaques();
            this.id = pokemonEntity.getId(); 
         }   
    }
     
     public PokemonDTO(String nombre, String descripcion, double peso, double altura, PokemonType tipo, PokemonType debilidad, List<AttackEntity> ataques, Long id )
    {
         this.nombre = nombre;
            this.descripcion = descripcion; 
            this.peso = peso; 
            this.altura = altura; 
            this.tipo = tipo;
            this.debilidad = debilidad;
            this.ataques = ataques;
            this.id = id; 
    }
     
     public PokemonEntity toEntity() 
     {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(this.getId());
        pokemonEntity.setNombre(this.getNombre());
        pokemonEntity.setDescripcion(this.getDescripcion());
        pokemonEntity.setPeso(this.getPeso());
        pokemonEntity.setAltura(this.getAltura());
        pokemonEntity.setTipo(this.getTipo());
        pokemonEntity.setDebilidad(this.getDebilidad());
        pokemonEntity.setAtaques(this.getAtaques());
        
        return pokemonEntity;
    }

      public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }
    
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    public double getPeso() 
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    public double getAltura()
    {
        return altura;
    }

    public void setAltura(double altura) 
    {
        this.altura = altura;
    }

    public PokemonType getTipo() 
    {
        return tipo;
    }

    public void setTipo(PokemonType tipo) 
    {
        this.tipo = tipo;
    }

    public PokemonType getDebilidad() 
    {
        return debilidad;
    }

    public void setDebilidad(PokemonType debilidad)
    {
        this.debilidad = debilidad;
    }

    public List<AttackEntity> getAtaques() 
    {
        return ataques;
    }

    public void setAtaques(List<AttackEntity> ataques) 
    {
        this.ataques = ataques;
    }
     
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
            
}
