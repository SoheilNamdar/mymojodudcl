package fr.sir.tpmaven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.clapper.classutil.ClassFinder;
import org.clapper.classutil.ClassInfo;

@Mojo( name = "count")
@Execute( goal = "count",
phase = LifecyclePhase.COMPILE)

public class ClassCounterMojo
extends AbstractMojo
{	
	@Parameter(defaultValue="french")
	String language;

	
@Parameter( defaultValue = "${project.build.directory}", readonly = true )
private File outputDirectory;

public void execute()
throws MojoExecutionException
{
	File f = outputDirectory;
	File f1 = new File(outputDirectory.getAbsolutePath(),"classes");
	List<File> files = new ArrayList<File>();
	files.add(f1);
	ClassFinder finder = new ClassFinder(scala.collection.JavaConversions.asScalaBuffer(files));
	if ("french".equals(language))
		this.getLog().info("nombre de classe " + finder.getClasses().size());
	else
		this.getLog().info("number of classe " + finder.getClasses().size());
	scala.collection.Iterator<ClassInfo> it = finder.getClasses();
	while (it.hasNext()){
		ClassInfo c = it.next();
		 if ("french".equals(language))
		 {
			 this.getLog().info("\t Pour la classe " + c.name());
			 this.getLog().info("\t \t Nbre attributs " + c.fields().size());
			 this.getLog().info("\t \t Nbre methodes " + c.methods().size());
		 }else{
			 this.getLog().info("\t For the class named " + c.name());
			 this.getLog().info("\t \t Number of filed " + c.fields().size());
			 this.getLog().info("\t \t Number of methods " + c.methods().size());
		 }
	} }
}
