Given a dictionary of words in a file.
Display the longest word with meaningful concatenations

I read the words file using BufferedInputStream.
Added at the read words to a hash set for fast search. Hash set provides O(1) search.

Iterated over the list of words and checked if the word is a valid concatenation of sub-words and verified 
it against the hash set. If the substrings are valid then we check for the next valid word and print both
of the words.

For each valid concatenated word I incremented the count for displaying the total valid concatenated words.

How to Test this project? 

Compile the java files.
Run the Driver file with a valid argument.
I also add the total time taken for retrieval of the results. 
It may vary depending on the Computer you run and heap space.

Development Environment: Used Eclispse Mars on a 64bit windows machine with default heapspace. Increased heapspace to 1024.

My test results:

No of Concatenated words:97107
Largest Concatenated word: ethylenediaminetetraacetates with size: 28
Second Largest Concatenated word: ethylenediaminetetraacetate with size: 27
Total time taken to retrieve the results:500 milliseconds(Can be lower on a better computer)
