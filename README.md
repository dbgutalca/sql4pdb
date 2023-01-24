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

<h1>Requirements</h1>
In order to use this tool it's necesary to meet the following requirements:

<ul>
  <li>Java version 8 (https://www.java.com/en/download/ie_manual.jsp)</li>
  <li>PostgreSQL installation (https://www.postgresql.org/download/)</li>
  <li> <b>Optional: </b>A local download of PDB files. You can find a download script in the file download.sh</li>
</ul>

<h1>Installation</h1>
<p><b>Create a new PDB database:</b>  To create a new PDB database, see option 4 in usage section.</p>

<p><b>Download the PDB2SQL Jar file:</b> Once downloaded, fill the configuration.conf file. In this file you must complete this information:</p>

<p><b>URL</b>= Direction where the postgreSQL database is located, i.e: localhost:5432/PDB_server</p>
<p><b>USER</b>= PostgreSQL username</p>
<p><b>PASSWORD</b>= PostgreSQL password</p>
<p><b>PDB_ID</b>= List of protein names separated by coma, i.e: [7ENG, 1AFK, 1AFR]</p>
<p><b>DIR</b>= Directory of your local PDB files, i.e: /home/pdb-files</p>

<h1>Usage</h1>
<p>To use PDB2SQL, open a command prompt in the directory where PDB2SQL is located, then put the following command: <b>java -jar PDB2SQL.jar.</b>  Once opened, there is a menu asking you which option do you want.</p>
<p><b>Option 1: To start PDB - SQL conversion.</b></p>
<p>Start the conversion of every PDB files stored in the directory given by the DIR option of configuration.conf file. Then you will see the current PDB file that is being parsed and the number of the current PDB file with the total PDB files stored locally. It may take a long time to store every PDB file.</p>
<p><b>Option 2: Fill database by a given list.</b></p>
<p>Download every PDB file wrote in PDB_ID option of configuration.conf file, then the tool is executed normally. Use this option if you want to store a personalized proteins.</p>
<p><b>Option 3: Create a sample database.</b></p>
<p>Creates a database with 11 PDB entries. Use this option if you want to check the functionality of the database.</p>
<p><b>Option 4: Create a new PDB database.</b></p>
<p>Use this option if it is the first time using the tool and you already have PostgreSQL installed. With this option, you will create a database with every table required for a further use. You must put in configuration.conf you URL without the database name and a slash ('/') at the end in order to create a new one, i.e localhost:5432/</p>
<h1>PDB files that are not considerated</h1>
<p>The following types of files are not considered during the execution of the application:</p>
<ul>
<li>DNA/RNA entries.</li>
<li>Entries with RNA or ELECTRON CRISTALOGRAPHY experiment.</li>
<li>Entries with aminoacids located in an alphanumeric position within a chain.</li>
<li>Entries that has a different aminoacid in a chain (Not standard).</li>
<li>Entries without aminoacid chains or without SEQRES record.</li>
</ul>
