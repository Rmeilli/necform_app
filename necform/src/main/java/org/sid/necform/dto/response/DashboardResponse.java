package org.sid.necform.dto.response;

public record DashboardResponse(

        long formations,

        long sessions,

        long utilisateurs,

        long inscriptions,

        long entreprises,

        long demandes

) {
}