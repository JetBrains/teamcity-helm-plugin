package ekoshkin.teamcity.clouds.kubernetes;

import ekoshkin.teamcity.clouds.kubernetes.connector.PodPhase;
import jetbrains.buildServer.clouds.InstanceStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ekoshkin (koshkinev@gmail.com) on 13.06.17.
 */
public class InstanceStatusUtils {
    @NotNull
    public static InstanceStatus mapPodPhase(@NotNull PodPhase podPhase) {
        switch (podPhase) {
            case Pending:
                return InstanceStatus.STARTING;
            case Running:
                return InstanceStatus.RUNNING;
            case Succeeded:
                return InstanceStatus.STOPPED;
            case Failed:
                return InstanceStatus.ERROR;
            case Unknown:
                return InstanceStatus.UNKNOWN;
            default:
                return InstanceStatus.UNKNOWN;
        }
    }
}
