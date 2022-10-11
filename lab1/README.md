# java-technologies-labs

# Compulsory
Create a servlet that receives a word and returns an HTML page containing the letters of that word presented as an ordered list.

# Homework
It receives an integer as a parameter, called size, and it returns all the permutations of length size of the given letters. If the size is 0 (default), it will return all the sequences.
If the servlet has access to a server-side file containing a list of acceptable words (a dictionary), it will return only the sequences forming valid words. This list should be large enough; you may use aspell to generate a text file containing English words, or anything similar: WordNet, dexonline, etc.
For example, if the servlet receives the leters a,a,j,v and the size is 0, it may return the list aa, ava, java (assuming it uses an en english dictionary).

The servlet invocation will be done using a simple HTML form. The servlet will return the response as an HTML page.

# Bonus
// TODO

# How to run?
http://localhost:8080/MyFancyServlet/permutations.html

http://localhost:8080/MyFancyServlet/word?word=mister&size=3&submit=Submit