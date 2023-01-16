# sql4pdb

<p> SQL4PDB (http://dbg.utalca.cl/pdbSQL/Home.php) is a web application that allows to execute SQL queries on a relational database with information obtained from PDB files (https://www.rcsb.org/)). The database contains information about proteins, chains, amino acids, ligands, sheets, bonds and strands.

SQL4PDB provides a simple user interface to query the data by using the SQL query language. It allows the user to execute simple queries like "obtain all the proteins of a given classification", or complex queries like "obtain all the proteins interacting with two different ligands". </p>

<p> On this repository you will find a tool called PDB2SQL which converts a PDB file into SQL instructions and stores them in a relational database. This tool is used to fill the database that SQL4PDB uses.</p>

<h1> PDB2SQL </h1>
<p> PDB2SQl is a tool programed in Java that reads a PDB file using the BioJava PDBFileParser class for some records and also works with PostgreSQL database. This tool obtains every field of a record based on the Atomic Coordinate Entry Format Description Version 3.3 (https://www.wwpdb.org/documentation/file-format-content/format33/v3.3.html) and the parsed records are the following: </p>

<ul>
  <li> HEADER </li> 
  <li> SOURCE </li> 
  <li> KEYWDS </li> 
  <li> TITLE </li>
  <li> EXPDTA </li>
  <li> SEQRES </li> 
  <li> HET </li> 
  <li> HETNAM </li> 
  <li> HETSYN </li> 
  <li> FORMUL </li> 
  <li> HELIX </li> 
  <li> SHEET </li> 
  <li> SSBOND </li> 
  <li> ATOM </li> 
  <li> HETATM </li> 
  <li> MASTER </li> 
</ul>
<p> PDB2SQL has three principal functions: to create a complete PDB database by a local data installation, to create a sample database and to create a database by a list of protein names.</p>

<h1>Database</h1>
<h1>Requirements</h1>
In order to use this tool it's necesary to meet the following requirements:

<ul>
  <li>Java version ...</li>
  <li>PostgreSQL installation</li>
  <li> <b>Optional: </b>A local download of PDB files</li>
</ul>

<h1>Installation</h1>
<p>After meeting the requirements, download the PDB2SQL Jar file and complete the configuration.conf file. In this file you must complete this information:</p>
<ul>
  <li>URL= Direction where the postgreSQL database is located, i.e localhost:5432/PDB_server</li>
  <li>USER= </li>
  <li> <b>Optional: </b>A local download of PDB files</li>
</ul>

<h1>Usage</h1>
<h1>PDB files that are not considerated</h1>
