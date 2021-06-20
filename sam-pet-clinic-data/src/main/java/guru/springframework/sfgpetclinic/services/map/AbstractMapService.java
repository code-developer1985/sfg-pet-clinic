package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity,ID extends  Long> {
  protected Map<Long,T> map= new HashMap<>();

  Set<T> findAll(){
    return new HashSet<>(map.values());
  }

  T findById(ID id){
    return map.get(id);
  }

  T save(T object){
    if (object!=null){
      if(Objects.isNull(object.getId()))
      {
        object.setId(getNextId());
      }
      map.put(object.getId(),object);
    }else
    {
      throw new RuntimeException("Object can not be null");
    }
    return object;
  }

  void delete(T object){
    map.entrySet().removeIf(entry -> entry.getValue().equals(object));
  }
  void deleteById(ID id){
    map.remove(id);
  }

  private Long getNextId(){
    if (map.keySet().isEmpty())
      return 10L;
    else
    return Collections.max(map.keySet())+1;
  }
}
