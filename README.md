
<h1> SQL4PDB </h1>
<p> SQL4PDB is a tool programed in Java that reads a PDB file using BioJava PDBFileParser class for some records and also works with PostgreSQL database. This tool obtains every field of a record based on the Atomic Coordinate Entry Format Description Version 3.3 (https://www.wwpdb.org/documentation/file-format-content/format33/v3.3.html) and the parsed records are the following: HEADER, SOURCE, KEYWDS, TITLE, EXPDTA, SEQRES, HET, HETNAM, HETSYN, FORMUL, HELIX, SHEET,  SSBOND, ATOM, HETATM, MASTER. Also, this application allows you to create a new PDB database on your computer with an option.</p>

<h1>Application download</h1>
<p>The application and every file needed to execute it is contained in SQL4PDB rar file, you can download it by pressing the following link: <br></p>
<span><img src="https://user-images.githubusercontent.com/31783838/113711005-ccdc3880-96ba-11eb-9d3e-d4c030020985.png" />
<a href = "https://github.com/dbgutalca/sql4pdb/raw/main/SQL4PDB.rar"> <b>SQL4PDB.rar</b></a></span>

<h1>Requirements</h1>
In order to use this tool it's necesary to meet the following requirements:

<ul>
  <li>Java version 8 (https://www.java.com/en/download/ie_manual.jsp)</li>
  <li>PostgreSQL installation (https://www.postgresql.org/download/)</li>
  <li> <b>Optional: </b>A local download of PDB files. You can find a download script in the file download.sh</li>
</ul>

<h1>Database creation</h1>
<p>On this section, you will create a new database with this tool step by step.</p>
<p><b>Step 1:</b> Download the SQL4PDB application. Click the link in application download section.</p>
<p><b>Step 2:</b> Fill the configuration.conf file. This is the most important file and it must be filled with this information for a correct behavior of the application. You can open this file with every text editor.</p>
<ul>
<li><p><b>URL</b>= Direction where the postgreSQL database is located. To create a new database in your local PC use the following URL: localhost:5432.</p></li>
<li><p><b>USER</b>= PostgreSQL username. Usually, the default username created in the installation of PostgreSQL is <b>postgres</b></p></li>
<li><p><b>PASSWORD</b>= PostgreSQL password.</p></li>
<li><p><b>PDB_ID</b>= List of protein names separated by coma, i.e: [7ENG, 1AFK, 1AFR].</p></li>
<li><p><b>DIR</b>= Directory of your local PDB files, i.e: /home/pdb-files.</p></li>
</ul>
<p><b>Step 3:</b> Open a command prompt in SQL4PDB directory and put the command <b>java -jar SQL4PDB.jar.</b> </p>
<p><b>Step 4:</b> Select option 1 and enter a name for the database. At the end of this execution, you will have created the relational database with every table required to parse a PDB file into the database. Then, restart the application. </p>
<p><b>Step 5:</b> At this point, you will have an empty database created, so you can use the other options to fill the database.</p>

<h1>Options</h1>

<p>To use SQL4PDB, open a command prompt in the directory where SQL4PDB is located, then put the following command: <b>java -jar SQL4PDB.jar</b> Once opened, there is a menu asking you which option do you want.</p>
<p><b>Create a new PDB database.</b></p>
<p>This option will appear if you did not put any database name in the configuration file, it will create a new database with any table required to work. After this execution, the application will change the DIR option with the new database created.</p>
<p><b>Convert local PDB directory.</b></p>
<p>Start the conversion of every PDB files stored in the directory given by the DIR option of configuration.conf file. Then you will see the current PDB file that is being parsed and the number of the current PDB file with the total PDB files stored locally, the format of the files must be PDB compressed in GZ. It may take a long time to store every PDB file. Use this option if you want to have every posible PDB entry in the database.</p>
<p><b>Fill database by a given list.</b></p>
<p>Download every PDB file wrote in PDB_ID option of configuration.conf file, then the tool is executed normally. The protein list names must be between brackets ('[]') and separated by coma. Use this option if you want to store a personalized proteins.</p>
<p><b>Create a sample database.</b></p>
<p>Creates a database with 10 sample PDB entries. Use this option if you want to check the functionality of the database.</p>


<h1>PDB files download</h1>
<p>To download every pdb file in the official PDB database, you have to use the script <b>download.sh</b> located in SQL4PDB rar file. For this, open a command prompt, go to the download.sh directory and the put the command <b>./download.sh</b>. For windows users, you have to install WSL (https://learn.microsoft.com/en-us/windows/wsl/install) first an then do the same command secuence. After that, you will have every pdb file located in a folder named <b>pdb-files</b>. Use this directory in DIR section in configuration.conf file in order to store every posible pdb file in the database. The pdb files will be downloaded as a compressed .ENT format but it works and have the same information of a .PDB file</p>

<h1>PDB files that are not considerated</h1>
<p>The following types of files are not considered during the execution of the application:</p>
<ul>
<li>DNA/RNA entries.</li>
<li>Entries with RNA or ELECTRON CRISTALOGRAPHY experiment.</li>
<li>Entries with aminoacids located in an alphanumeric position within a chain.</li>
<li>Entries that has a different aminoacid in a chain (Not standard).</li>
<li>Entries without aminoacid chains or without SEQRES record.</li>
</ul>
