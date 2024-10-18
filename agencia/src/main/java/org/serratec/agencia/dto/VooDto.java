package org.serratec.agencia.dto;
import org.serratec.agencia.model.StatusVoo;
import org.serratec.agencia.model.Voo;
import org.serratec.agencia.model.Reserva;

public record VooDto (
    Long id,
    String compania,
    StatusVoo statusVoo,
    Reserva reserva
) {

    public Voo toEntity() {
        Voo voo = new Voo();
        voo.setId(this.id);
        voo.setCompania(this.compania);
        voo.setStatusVoo(this.statusVoo);
        voo.setReserva(this.reserva);
        return voo;
    }

    public static VooDto toDto(Voo voo) {
        return new VooDto(voo.getId(), voo.getCompania(), voo.getStatusVoo(), voo.getReserva());
    }
}

