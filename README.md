## Modified ZOMBIE (M-ZOMBIE) DSL in Scala
ZOMBIE is a programming language designed for Necromancers, particularly evil ones. ZOMBIE is an acronym, and stands for Zombie-Oriented Machine-Being Interface Engine.
This project implements a **slightly modified version** of ZOMBIE language using the convenient and flexible features of Scala.

## TODOs

1. ~~fill in all coordinators information~~
2. ~~implement conditions of flow control~~
3. ~~implement repetitions of flow control~~
4. implement Operators: remembering, rend, turn
5. implement full set of statements delineate matched pairs: summon / animate, summon / bind, summon / disturb
6. implement matched pairs that supports recursions: task / animate, task / bind
7. implement statement stacks (very important)
8. implement other entity types: ghost, vampire

## Coordinators
1. Xin Lin (xl5224, jimmylin@utexas.edu)
2. Spencer Bull (sgb695, sbull@cs.utexas.edu)
3. Chuqi Zhou (cz4792, cz4792@utexas.edu)


## Usage
To compile the entire codebase of the zombie package, please do

	make
or 

	scalac src/*.scala -d bin/
	
All compiled binary files will be placed under the `bin/` directory.

To run pre-defined tests, you can do

	make [test_name]
or

	scala -cp bin/ [test_name]

## M-ZOMBIE Language

###Entity Declaration
Entities are the basic elements of a ZOMBIE program. Valid ZOMBIE programs must declare at least one entity.
Entities are declared with the following structure:

	entity-type (entity-name) { entity-statements }

Entity-type is one of the following: **ZOMBIE**, **GHOST**, **VAMPIRE**, **DEMON**, **DJINN**.

### **ZOMBIE**
- may be **declared** and then **animated**. An **animated** zombie can be expected to do whatever it is commanded to do, straight away.
- process their active tasks in sequence, beginning from the first task defined, as quickly as they can. They perform each task exactly once.
- animated by necromancers.

### **GHOST**
- remain behind either because of an unconsecrated death or to avenge an injustice.
- may be declared and then **disturbed**. A **disturbed**, and hence restless, ghost will eventually do what is asked of it.
- process their active tasks in sequence, beginning from the first task defined, but they may wait for an undefined time before beginning and between each task.
- eventually perform each task exactly once.

### **VAMPIRE**
- deliberately pervert their own death to remain active and wreak evil.
- may be declared
- do not require animating or disturbing, and will do what is asked of them, but not necessarily in the order requested.
- process their active tasks in random order, as quickly as they can
- perform each task exactly once, and complete one task before beginning the next.

### **DEMON**
- may be declared
- do not require animating or disturbing
- do what is asked of it, if the proper precautions are taken, but may summon other demons to help it. This may or may not be a good thing.
- process their active tasks in random order, as quickly as they can.
- may decide to perform tasks multiple times before becoming inactive, but will perform each task at least once.
- They may perform multiple tasks at the same time.
- They may also summon additional demons exactly like themselves.

### **DJINN**
- free-willed entities of capricious nature
- commanded by a person who controls an object which is bound to them in an unbreakable connection
- Woe betide if you lose control of the object, however, since djinn harbour great grudges against those who dare to command them. Some of the most powerful djinn can grant wishes.
- may be declared
- do not require animating or disturbing
- will do what is asked of it, if the proper precautions are taken, but may summon other demons to help it. This may or may not be a good thing.

Entity-name is any well-formed identifier string.

Entity-statements is a list of any valid statements, which may include entity declarations.

###Entity Declaration Statements
Some statements delineate matched pairs, which may be nested to any level. All the following combinations are properly matched pairs:

- SUMMON / ANIMATE
- SUMMON / BIND
- SUMMON / DISTURB

###Task Statements

- `FORGET (entity-name)`
> Instructs the entity to forget its remembered data value.

- `MOAN (entity-name)`
> Instructs the named entity to moan its remembered data value, and to keep remembering it.

- `REMEMBER ([entity-name,] values)`
> Instructs the entity to remember the sum of the values in the statement stack. Since a zombie can only remember one thing at a time, this causes it to forget any previously remembered value.

- `SAY ([entity-name,] text)`
> Print the text to the standard output. (It doesn't matter what entity does this, as the result is the same.)

## Flow Control
- `SHAMBLE ... UNTIL variable`
> Causes the entity to repeat the statements between shamble and until until the variable evaluates to true.

- `SHAMBLE ... AROUND`
> Causes the entity to repeat the statements between shamble and around in an infinite loop.

- `STUMBLE`
> Causes the current task to become inactive immediately.

- `TASTE variable GOOD ... BAD ... SPIT`
> If the variable evaluates to true, causes the entity to perform the statements between good and bad, otherwise perform the statements between bad and spit.

## References
1. Original Zombie Specification:

    http://www.dangermouse.net/esoteric/zombie.html

2. Scala API for 2.12.0.
