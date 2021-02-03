The main Python module containing the analysis job (which will be sent to the Spark cluster), is jobs/analysis_job.py.
Any external configuration parameters required by analysis.py are stored in JSON format in configs/config.json.
Additional modules that support this job can be kept in the dependencies folder.
In the project's root is build_dependencies.sh, which is a bash script for building these dependencies into a zip-file
to be sent to the cluster (packages.zip). 

JupFiles contains *.ipynb files which can be loaded in Jupyter notebook or *.py files that can be used in visual studio/ipython.

Running the Job code:
To run the analysis job:(Note** It was tested on windows setup -local mode)
--Instructions to setup spark on windows can be found in SparkOnWindows_Setup
.\spark-submit --master local --py-files C:\\Users\\Win10\\Desktop\\SampleProject\\packages.zip 
--files C:\\Users\\Win10\\Desktop\\SampleProject\\configs\\config.json 
C:\\Users\\Win10\\Desktop\\SampleProject\\jobs\\analysis_job.py

The same could be run on 
--spark stand alone cluster (with 2 or multiple worker nodes ) on linux machines.
--sample config files & instructions are in folder 'SparkStandAlone_Setup'
--additional properties could be specified on command line or via configs file

.\spark-submit --master local --py-files <path_to_your_project>/packages.zip 
--files <path_to_config>/configs/config.json <path_to_jobfile>/analysis_job.py

Additional options:
AWS EMR Cluster
Databricks community cloud
Spark integrated with Hadoop-YARN Cluster

More about Job
As per best practices its better to have the 'Transformation' step isolated from the 'Extract' and 'Load' steps,
into its own function - taking input data arguments in the form of DataFrames and returning the transformed data 
as a single DataFrame. Then, the code that surrounds the use of the transformation function in the main() job function,
is concerned with Extracting the data, passing it to the transformation function and then Loading (or writing) 
the results to their ultimate destination. 
Testing is also simplified in this way, as mock or test data can be passed to the transformation function 
and the results can be verified.

Configurations are being passed via configs/config.json.
Other alternative would be passing on command line while submitting spark-submit job.

Dependencies management is done via dependencies folder.
I am sending all dependencies as a zip archive along with the job.
In standalone cluster, dependencies/input files/config files etc.. all have to exist on 'worker nodes' or 
a shared location/mount point which is accessible.

Provided build_dependencies.sh bash script for automating the production of packages.zip, 
based on a given list of dependencies documented in Pipfile and managed by the pipenv python application has been used,
however that has been taken from internet.



