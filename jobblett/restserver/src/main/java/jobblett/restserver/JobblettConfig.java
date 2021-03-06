package jobblett.restserver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import jobblett.core.Workspace;
import jobblett.json.JobblettPersistence;
import jobblett.restapi.WorkspaceService;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JobblettConfig extends ResourceConfig implements PropertyChangeListener {
  private Workspace workspace;

  /**
   * Initialize a instance of Group with a groupName and groupID.
   *
   * @param workspace for JobblettConfig
   */
  public JobblettConfig(Workspace workspace) {
    setWorkspace(workspace);
    register(WorkspaceService.class);
    register(JobblettModuleObjectMapperProvider.class);
    register(JacksonFeature.class);
    register(new AbstractBinder() {
      @Override protected void configure() {
        bind(JobblettConfig.this.workspace);
      }
    });
  }

  public JobblettConfig() {
    this(createDefaultWorkspace());
  }

  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
    this.workspace.addListener(this);
  }

  private static Workspace createDefaultWorkspace() {
    return new JobblettPersistence().readValue(Workspace.class);
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    new JobblettPersistence().writeValueOnDefaultLocation(workspace);
  }
}
