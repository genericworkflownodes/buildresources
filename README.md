# buildresources

Note: This repository is **mostly** deprecated. We completely moved to maven tycho for building. There might be some remnants of buckminster here and there
but we do not maintain building with it anymore. Also, the update site is now automatically generated with the GKN node generation recursive option,
to collect every feature and plugin in a folder. So de.openms.update will be generated on-the-fly and is not needed from here anymore.


This repository contains resources for building GKN and related plugins with e.g. buckminster.

Its only current use are the contents of de.openms.thirdparty that will be copied into the generated plugin sources (before build) such that the old nodes will be found
with the new identifiers.
It would be too specific to include this as an option to the plugin generation mechanism.
