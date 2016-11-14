src_dir = src
bin_dir = bin
zsc_dir = zscripts

all: 
	scalac $(src_dir)/*.scala $(zsc_dir)/*.scala -d $(bin_dir)
	scala -cp $(bin_dir) HelloWorld 
	scala -cp $(bin_dir) Simple

shamble: around until

around:
	scala -cp $(bin_dir) ShambleAround

until: 
	scala -cp $(bin_dir) ShambleUntil

clean:
	rm $(bin_dir)/*.class
