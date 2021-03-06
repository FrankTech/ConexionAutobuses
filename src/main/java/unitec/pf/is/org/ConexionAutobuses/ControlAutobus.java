package unitec.pf.is.org.ConexionAutobuses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class ControlAutobus {


    @Autowired RepositorioAutobus repoABus;


    // Metodo para buscar todos los registro
    @CrossOrigin
    @RequestMapping(value = "/Autobus", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public ArrayList<Autobus> buscarTodos(){
        return (ArrayList<Autobus>)repoABus.findAll();
    }

    //Metodo para buscar por id
    @CrossOrigin
    @RequestMapping(value = {"/Autobus/{idAutobus}"}, method = RequestMethod.GET, headers = {"Accept=application/json"})
    public Optional<Autobus> buscarPorId (@PathVariable String idAutobus){
        return repoABus.findById(idAutobus);
    }

    //Metodo para guardar
    @CrossOrigin
    @RequestMapping(value = "/Autobus/{marcaAutobus}/{tipoAutobus}/{numeroAsientos}", method = RequestMethod.POST, headers = {"Accept=application/json"})
    public Estatus guardarAutobus(@PathVariable String marcaAutobus, @PathVariable String tipoAutobus, @PathVariable String numeroAsientos){
        repoABus.insert(new Autobus(marcaAutobus, tipoAutobus, numeroAsientos));
        return new Estatus(true,"Guardado con exito");
    }

    //Metodo para actualizar
    @CrossOrigin
    @RequestMapping(value = "/Autobus/{idAutobus}/{marcaAutobus}/{tipoAutobus}/{numeroAsientos}", method = RequestMethod.PUT, headers = {"Accept=application/json"})
    public Estatus actualizar(@PathVariable String idAutobus, @PathVariable String marcaAutobus, @PathVariable String tipoAutobus, @PathVariable String numeroAsientos){
        repoABus.save(new Autobus(idAutobus,marcaAutobus,tipoAutobus,numeroAsientos));
        return new Estatus(true, "Actualizacion con exito");
    }

    //Metodo para borrar registro
    @CrossOrigin
    @RequestMapping(value = "/Autobus/{idAutobus}", method = RequestMethod.DELETE, headers = {"Accept=application/json"})
    public Estatus borrarAutobus(@PathVariable String idAutobus){
        repoABus.delete(new Autobus(idAutobus));
        return new Estatus(true, "Borrado con exito");
    }

}
