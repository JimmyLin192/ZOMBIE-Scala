src_dir = src
bin_dir = bin

all: 
	scalac $(src_dir)/*.scala -d $(bin_dir)
	scala -cp $(bin_dir) ZombieHelloWorld 
