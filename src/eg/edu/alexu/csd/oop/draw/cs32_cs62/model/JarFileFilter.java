package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JarFileFilter extends FileFilter {
	@Override
	public boolean accept(File file) {

		if (file.isDirectory()) {
			return true;
		}
		if (file.getName().endsWith(".jar")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return ".jar";
	}
}