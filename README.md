## Opdracht: Task Queue System - HelioBit
### Probleemanalyse
1. Er moet een queue-systeem, oftewel wachtrij systeem, worden ontwikkeld die taken in de volgorde van toevoeging uitvoert.
2. Deze taken moeten kunnen verschillen in uitvoertijd en er kunnen maximaal drie tegelijkertijd uitgevoerd worden.
3. Zodra een taak voltooid is, moet de eerstvolgende taak uit de wachtrij worden gehaald.
4. Het systeem moet doorgaan met het uitvoeren van taken totdat er geen taken meer in de wachtrij staan.

### Benadering van de oplossing
- Voor het in de wachtrij zetten van de taken, kan ik het beste de datastructuur queue gebruiken. Het werkt volgens FIFO, first in first out, de taken worden bij deze structuur op volgorde van toevoeging geplaatst.
- Om taken gelijktijdig te laten lopen, kan ik gebruik maken van threading. Elke taak wordt dan door een thread uitgevoerd.  Verschillende uitvoertijden kunnen worden gesimuleerd door de threads voor een bepaalde tijd in slaap te zetten
- Om drie taken tegelijk uit te voeren, kan ik een groepje maken van drie threads waar de taken worden uitgevoerd.

### Pseudo code
```
Maak een queue aan voor taken
Maak een groep van 3 threads aan

Maak lijst van taken aan

VOOR elke taak in de lijst:
    Voeg toe aan wachtrij

ZOLANG er taken in wachtrij staan:
    ALS er een thread beschikbaar is:
        Pak eerste taak uit wachtrij
        Geef taak aan vrije thread
        Thread voert taak uit:
            Wacht opgegeven tijd
            Slaap voor uitvoertijd van taak
        Thread wordt weer beschikbaar

WANNEER wachtrij leeg is:
    Wacht tot alle threads klaar zijn
    Sluit systeem af
```
### Implementatie oplossing
De taal die ik heb gekozen voor deze opdracht is Java. 

Java heeft verschillende implementaties die ik goed kan gebruiken voor deze opdracht:

Runnable interface voor de taken 
- Standaard Java interface voor het maken van taken die door threads uitgevoerd kunnen worden.

BlockingQueue als Queue datastructure implementatie:
- Biedt ingebouwde thread safety 
- Perfect voor producer-consumer scenarios zoals deze opdracht

ExecutorService voor thread management 
- Ingebouwd thread pool mechanisme. Zorgt ervoor dat er niet meer threads worden aangemaakt dan nodig. Kan op max 3 worden gezet zodat er altijd maar drie threads zijn die taken uitvoeren.
- Handelt parallele uitvoering automatisch af
- Voorkomt de complexiteit van eigen thread beheer implementatie
