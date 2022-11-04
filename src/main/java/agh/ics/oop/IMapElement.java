package agh.ics.oop;

// Moja odpowiedź od 5.11:
// Według mnie nie na etapie na, którym aktualnie znajduje się projekt nie jest to wskazane. Klasa Animal oraz Grass
// nie mają wiele wspólnego kodu, dlatego uważam że interface IMapElement w zupełności wystarczy żeby uprościć kod w
// klasach aktualnie implementujących interface IWorldMap.

public interface IMapElement {
    /**
     * Return object position.
     *
     */
    public Vector2d getPosition();

    public void setPosition(Vector2d position);

}
