## Modified ZOMBIE (M-ZOMBIE) DSL in Scala
ZOMBIE is a programming language designed for Necromancers, particularly evil ones. ZOMBIE is an acronym, and stands for Zombie-Oriented Machine-Being Interface Engine.
This project implements a **slightly modified version** of ZOMBIE language using the convenient and flexible features of Scala. 

## TODOs

1. fill in all coordinators' information
2. implement conditions of flow control
3. implement repetitions of flow control
4. implement Operators: remembering, rend, turn
5. implement full set of statements delineate matched pairs: summon / animate, summon / bind, summon / disturb
6. implement matched pairs that supports recursions: task / animate, task / bind
7. implement statement stacks (very important)
8. implement other entity types: ghost, vampire

## Coordinators
1. Xin Lin (XL5224, jimmylin@utexas.edu)
2. Spencer Bull(sgb695, sbull@cs.utexas.edu)
3. Chu-qi Zhou ()


## Usage


## M-ZOMBIE Language

###Entity Declaration
Entities are the basic elements of a ZOMBIE program. Valid ZOMBIE programs must declare at least one entity.
Entities are declared with the following structure:

	entity-type (entity-name) { entity-statements }

Entity-type is one of the following: **ZOMBIE**, **GHOST**, **VAMPIRE**, **DEMON**, **DJINN**.

Entity-name is any well-formed identifier string.

Entity-statements is a list of any valid statements, which may include entity declarations.

###Entity Declaration Statements
Some statements delineate matched pairs, which may be nested to any level. All the following combinations are properly matched pairs:

- summon / animate
- summon / bind
- summon / disturb

###Task Statements

- forget (entity-name)
> Instructs the entity to forget its remembered data value.

- moan (entity-name)
> Instructs the named entity to moan its remembered data value, and to keep remembering it.

- remember ([entity-name,] values)
> Instructs the entity to remember the sum of the values in the statement stack. Since a zombie can only remember one thing at a time, this causes it to forget any previously remembered value.

- say ([entity-name,] text)
> Print the text to the standard output. (It doesn't matter what entity does this, as the result is the same.)

## References 
1. Original Zombie Specification: 

    http://www.dangermouse.net/esoteric/zombie.html

2. Scala API for 2.12.0.
