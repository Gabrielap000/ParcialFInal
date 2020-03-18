# Parcial práctico - Sección 3 - 202010

## Instrucciones

1. Haga un fork de este repositorio
2. Clone el repositorio bifurcado en su máquina virtual
3. Abra los proyectos en Netbeans
4. En Netbeans vaya a _Services > Databases > JavaDB_ y cree una base de datos que se llame _pokemon_ (los demás campos déjelos en blanco)

## Punto 1 (30%). Persistencia

Esta aplicación tiene el propósito de crear un **PokeDesk** para gestionar la información de los Pokemons.

(5%) Cree la entidad _PokemonEntity_ en la carpeta correspondiente. Un Pokemon tiene un nombre, una descripción, un peso, una altura, un tipo (Fuego, Agua, Aire, Tierra, Roca), un tipo de debilidad y un id de tipo _Long_ que representa su llave primaria. 

**Nota: Enum de tipo de pokemon (PokemonType) ya esta creado en el proyecto.**

(5%) Un Pokemon tiene por lo menos un ataque, el cual consta de un nombre y de un daño el cuál es un valor numérico de 10 a 100. 

(10%) Realice la implementación de la persistencia de los Pokemons. El archivo de persistencia debe tener por lo menos la creación, obtener todos los pokemons y obtener un pokemón por nombre.

(10%) Cree 1 prueba unitarias en la clase correspondiente, para el método **crear** un pokemon, los cuáles validan si está correcta la implementación de la persistencia de la entidad.

Ejecute la prueba y valide que pasa correctamente.

## Punto 2 (50%). Lógica

Usted debe crear la lógica de la aplicación que cubra las reglas de negocio para la entidad _PokemonEntity_. Las reglas de negocio para **crear** un Pokemon son:

* No puede haber un pokemón con el nombre repetido
* Un pokemón de tipo x no puede tener una debilidad de tipo x.
* Un pokemón debe tener por lo menos un Ataque.
* Un ataque sólo puede tener un valor númerico entre 10 y 100.

(30%) Crear el método en la capa de lógica que valide las reglas de negocio y solicita persistir en caso que todas pasen. (Sólo para método crear)

(20%) Crear al menos dos pruebas unitarias: una que valida el escenario ideal en que todas las reglas de negocio se aprueban, y otra en que valide cuando una regla de negocio falla. Si las reglas de negocio se cumplen, se debe llamar la persistencia para que el objeto sea persistido, de lo contrario debe lanzar una excepción _BusinessLogicException_ con un mensaje donde se especifique el problema.

## Punto 3 (20%). Pruebas de integración

En la aplicación le hemos brindado parte de la capa REST API para probar. Para esto, en la clase _PokemonDTO_ usted debe:

(5%) Incluir los atributos correspondientes y un constructor vacío

(5%) Crear el método _toEntity_ que retorna un objeto _PokemonEntity_ con los datos del objeto _PokemonDTO_. 

(5%) Agregue el método constructor que recibe un _PokemonEntity_ y hace el mapeo correspondiente entre ambas clases.

En la clase _PokemonResource_ usted debe:

(5%) Modificar el método _createPokemon_ para que llame al método de la lógica que crea el pokemón, y retorne al usuario el nuevo pokemón creado.
 
### Prueba 1. Creación correcta Pokemon:

```
Method: POST
URL: http://localhost:8080/s3_pokemon-api/api/pokemons
Body:
{
	"name": "Alvisaur",
	"description": "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
	"height": 400,
	"weight": 20,
	"type": "GRASS",
	"weakness": "FIRE",
	"attacks": [{"name": "vines attack", "damage": 80}]
}
Response: 200
```

### Prueba 2. Creación incorrecta Pokemon (Pokemon repetido):

```
Method: POST
URL: http://localhost:8080/s3_pokemon-api/api/pokemons
Body:
{
	"name": "Alvisaur",
	"description": "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
	"height": 400,
	"weight": 20,
	"type": "GRASS",
	"weakness": "FIRE",
	"attacks": [{"name": "vines attack", "damage": 80}]
}
Response: 412
"Pokemon con el nombre Alvisaur ya existe!"
```


## Entrega

1. Agregue los pantallazos de las pruebas de Postman a la carpeta images de su repositorio

2. Haga commit y push a la rama master

3. Cree un release de su código con el nombre "Parcial1_2603". 

4. Suba el archivo zip del release como respuesta a la evaluación en SICUA



![Bulbasaur](imagenes/pokemons/001.png?raw=true=100x100 "Bulbasaur")
![Chamander](imagenes/pokemons/004.png?raw=true=100x100 "Chamander")
![Squirtle](imagenes/pokemons/007.png?raw=true=100x100 "Squirtle")

Éxitos totales.


Jhonatan A
