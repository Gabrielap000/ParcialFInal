/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mg.paez
 */
public class PokemonEntity extends BaseEntity implements Serializable
{
    private String nombre;
    
    private String descripcion;
    
    private double peso;
    
    private double altura; 
    
    private PokemonType tipo; 
    
    private PokemonType debilidad; 
    
    @PodamExclude
    @OneToMany(mappedBy = "pokemon")
    private List<AttackEntity> ataques = new ArrayList<>();
   
    @Id
    private Long id; 

    public List<AttackEntity> getAtaques() 
    {
        return ataques;
    }

    public void setAtaques(List<AttackEntity> ataques) 
    {
        this.ataques = ataques;
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

    @Override
    public Long getId() 
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }        
}
