
<h1> PDB2SQL </h1>
<p> PDB2SQl is a tool programed in Java that reads a PDB file using the BioJava PDBFileParser class for some records and also works with PostgreSQL database. This tool obtains every field of a record based on the Atomic Coordinate Entry Format Description Version 3.3 (https://www.wwpdb.org/documentation/file-format-content/format33/v3.3.html) and the parsed records are the following: HEADER, SOURCE, KEYWDS, TITLE, EXPDTA, SEQRES, HET, HETNAM, HETSYN, FORMUL, HELIX, SHEET,  SSBOND, ATOM, HETATM, MASTER. Also, this application allows you to create a new PDB database on your computer with an option.</p>

<h1>Requirements</h1>
In order to use this tool it's necesary to meet the following requirements:

<ul>
  <li>Java version 8 (https://www.java.com/en/download/ie_manual.jsp)</li>
  <li>PostgreSQL installation (https://www.postgresql.org/download/)</li>
  <li> <b>Optional: </b>A local download of PDB files. You can find a download script in the file download.sh</li>
</ul>

<h1>Options</h1>

<p>To use PDB2SQL, open a command prompt in the directory where PDB2SQL is located, then put the following command: <b>java -jar PDB2SQL.jar</b> Once opened, there is a menu asking you which option do you want.</p>
<p><b>Option 1: Create a new PDB database.</b></p>
<p>Use this option if you don't have a PDB database created on your device. With this option, you will create a database with every table required for a further use. You must put in configuration.conf you URL without the database name and a slash ('/') at the end in order to create a new one, i.e localhost:5432/. <b>This option is used one time at the first use of the application</b>. After that, this option will not work because there is a database already created. In database creation section, there is a tutorial explaining how to create a new database using this option.</p>
<p><b>Option 2: To start PDB - SQL conversion.</b></p>
<p>Start the conversion of every PDB files stored in the directory given by the DIR option of configuration.conf file. Then you will see the current PDB file that is being parsed and the number of the current PDB file with the total PDB files stored locally. It may take a long time to store every PDB file. Use this option if you want to have every posible PDB entry in the database.</p>
<p><b>Option 3: Fill database by a given list.</b></p>
<p>Download every PDB file wrote in PDB_ID option of configuration.conf file, then the tool is executed normally. The protein list names must be between brackets ('[]') and separated by coma. Use this option if you want to store a personalized proteins.</p>
<p><b>Option 4: Create a sample database.</b></p>
<p>Creates a database with 11 sample PDB entries. Use this option if you want to check the functionality of the database.</p>


<h1>Database creation</h1>
<p>On this section, you will create a new database with this tool step by step.</p>
<p><b>Step 1:</b> Download the PDB2SQL Jar file. Click the code button of github and the download ZIP.</p>
<p><b>Step 2:</b> Fill the configuration.conf file. This is the most important file and it must be filled with this information for a correct behavior of the application.</p>
<ul>
<li><p><b>URL</b>= Direction where the postgreSQL database is located. To create a new database in your local PC use the following URL: localhost:5432.</p></li>
<li><p><b>USER</b>= PostgreSQL username.</p></li>
<li><p><b>PASSWORD</b>= PostgreSQL password.</p></li>
<li><p><b>PDB_ID</b>= List of protein names separated by coma, i.e: [7ENG, 1AFK, 1AFR].</p></li>
<li><p><b>DIR</b>= Directory of your local PDB files, i.e: /home/pdb-files.</p></li>
</ul>
<p><b>Step 3:</b> Open a command prompt in the .jar directory and put the command <b>java -jar PDB2SQL.jar.</b> </p>
<p><b>Step 4:</b> Select option 1. At the end of this execution, you will have created the relational database with every table required to parse a PDB file into the database. Then, in URL section of configuration.conf file add the database name at the end of the line, i.e: localhost:5432/database_name.</p>
<p><b>Step 5:</b> At this point, you will have an empty database created so you can use the other options to fill the database.</p>

<h1>PDB files download</h1>
<p>To download every pdb file you have to use the script <b>download.sh</b>. For this, open a command prompt, go to the download.sh directory and the put the command <b>./download.sh</b>. For windows users, you have to install WSL (https://learn.microsoft.com/en-us/windows/wsl/install) first an then do the same command secuence. After that, you will have every pdb file located in a folder named <b>pdb-files</b>. Use this directory in DIR section in configuration.conf file in order to store every posible pdb file in the database. The pdb files will be downloaded as a compressed .ENT format but it works and have the same information of a .PDB file</p>
<h1>PDB files that are not considerated</h1>
<p>The following types of files are not considered during the execution of the application:</p>
<ul>
<li>DNA/RNA entries.</li>
<li>Entries with RNA or ELECTRON CRISTALOGRAPHY experiment.</li>
<li>Entries with aminoacids located in an alphanumeric position within a chain.</li>
<li>Entries that has a different aminoacid in a chain (Not standard).</li>
<li>Entries without aminoacid chains or without SEQRES record.</li>
</ul>
