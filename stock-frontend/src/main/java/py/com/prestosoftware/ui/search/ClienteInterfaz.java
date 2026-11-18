package py.com.prestosoftware.ui.search;

import java.awt.event.KeyEvent;

import py.com.prestosoftware.data.dtos.ClienteDTO;

public interface ClienteInterfaz {

	void getEntity(ClienteDTO c);

	/** Este metodo se ejecuta cuando se suelta una tecla */
//	void keyReleased(KeyEvent e);

	/**
	 * Este metodo funcionará solo cuando se presionan caracteres, si se presionan
	 * teclas como F1, F2, inicio etc no ejecutará ningun evento
	 */
//	void keyTyped(KeyEvent e);
	
}
