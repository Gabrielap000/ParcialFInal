/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mg.paez
 */
public class AttackEntity extends BaseEntity implements Serializable
{
   private String nombre; 
   
   private int dano; 
   
   @PodamExclude
   @ManyToOne
   private List<AttackEntity> pokemones = new ArrayList<>();

    public List<AttackEntity> getPokemones() {
        return pokemones;
    }

    public void setPokemones(List<AttackEntity> pokemones) {
        this.pokemones = pokemones;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getDano()
    {
        return dano;
    }

    public void setDano(int daño) 
    {
        this.dano = daño;
    } 
}
