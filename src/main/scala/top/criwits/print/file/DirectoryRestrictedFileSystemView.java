package top.criwits.print.file;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class DirectoryRestrictedFileSystemView extends FileSystemView {
    File root;
    File[] roots = new File[1];

    public DirectoryRestrictedFileSystemView(File path)
    {
        super();

        try
        {
            root = path.getCanonicalFile();
            roots[0] = root;
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException( e );
        }

        if ( !root.isDirectory() )
        {
            String message = root + " is not a directory";
            throw new IllegalArgumentException( message );
        }
    }

    @Override
    public File createNewFolder(File containingDir)
    {
        File folder = new File(containingDir, "New Folder");
        folder.mkdir();
        return folder;
    }

    @Override
    public File getDefaultDirectory()
    {
        return root;
    }

    @Override
    public File getHomeDirectory()
    {
        return root;
    }

    @Override
    public File[] getRoots()
    {
        return roots;
    }
}
