package de.openms.thirdparty.knime;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.knime.core.node.MapNodeFactoryClassMapper;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class OpenMSNodeFactoryClassMapper extends MapNodeFactoryClassMapper {

    @SuppressWarnings("unchecked")
    @Override
    protected Map<String, Class<? extends NodeFactory<? extends NodeModel>>> getMapInternal() {
        Map<String, Class<? extends NodeFactory<? extends NodeModel>>> map = new HashMap<>();
        Bundle bundle = FrameworkUtil.getBundle(getClass());
        Enumeration<URL> entries = bundle.findEntries("/target/classes/de/openms/thirdparty/knime/nodes/", "*Factory.class", true);
        while (entries != null && entries.hasMoreElements())
        {
            URL classURL = entries.nextElement();
            String path = classURL.getFile();
            String classnameNoBinFolderDots = path.replace("/target/classes/", "").replace(".class", "").replace('/', '.');
            String oldClassname = classnameNoBinFolderDots.replace("thirdparty.", "");
            
            Class<? extends NodeFactory<? extends NodeModel>> c;
            try {
                c = (Class<? extends NodeFactory<? extends NodeModel>>) bundle.loadClass(classnameNoBinFolderDots);
                map.put(oldClassname, c);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        Enumeration<URL> entries2 = bundle.findEntries("/bin/de/openms/thirdparty/knime/nodes/", "*Factory.class", true);
        while (entries2 != null && entries2.hasMoreElements())
        {
            URL classURL = entries2.nextElement();
            String path = classURL.getFile();
            String classnameNoBinFolderDots = path.replace("/bin/", "").replace(".class", "").replace('/', '.');
            String oldClassname = classnameNoBinFolderDots.replace("thirdparty.", "");
            
            Class<? extends NodeFactory<? extends NodeModel>> c;
            try {
                c = (Class<? extends NodeFactory<? extends NodeModel>>) bundle.loadClass(classnameNoBinFolderDots);
                map.put(oldClassname, c);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
