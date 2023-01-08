import java.util.*;

public interface Event {
  public List<String> toStringList();
  public void replayAndCheck(MBTA mbta);
}
