src_dir = src
bin_dir = bin
zsc_dir = zscripts

all:
	scalac $(src_dir)/*.scala $(zsc_dir)/*.scala -d $(bin_dir)

hello:
	scala -cp $(bin_dir) HelloWorld

simple:
	scala -cp $(bin_dir) Simple

shamble: around until

around:
	scala -cp $(bin_dir) ShambleAround

until:
	scala -cp $(bin_dir) ShambleUntil

taste: good bad

good:
	scala -cp $(bin_dir) TasteGood

bad:
	scala -cp $(bin_dir) TasteBad

OpRem:
	scala -cp $(bin_dir) OpRemembering

OpRend:
	scala -cp $(bin_dir) OpRend

OpTurn:
	scala -cp $(bin_dir) OpTurn

animate:
	scala -cp $(bin_dir) HelloWorldAnimate

task:
	scala -cp $(bin_dir) HelloWorldAnimateTask

bind:
	scala -cp $(bin_dir) Memeber1
fib:
	scala -cp $(bin_dir) Fib

clean:
	rm $(bin_dir)/*.class
