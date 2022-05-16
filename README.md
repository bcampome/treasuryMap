# Treasury Map [FR Version]

## Context 
Il s'agit d'un kata réaliser par mes soins. 
L'idée étant de ne pas passer trop de temps dessus tout en permetant de montrer mes connaissances.
(je compte le refaire de manière plus poussée dans quelques temps.)

## Commentaires (en vracs)

- Je suis parti du besoin final (Sprint 0 et Sprint 1 fusionnés)
- Il n'y a pas de notion de "durée" mais chaque action prends 1 slot de temps.
  (facilitera le point du sprint n°3)
- Le `Engine.java` à été développé en BDD pur, sans TDD. <br>
  Je voulais tester la qualité du code sans le TDD mais uniquement avec le BDD.<br>
  On pourra en parler avec plaisir.
- Le projet est en java 17.
- Il n'y a aucun contrôle de cohérence de données, ce n'était pas dans la spec' <br>
  Je pense qu'on peut faire un vrai sujet à ce propos. Qu'est ce qu'on test ? <br>
  La cohérence des inputs ? Les arguments passé par le dev ? Tout n'est pas à contrôler et ça nécessite une nouvelles
  étude.

## Reste à faire
- Pour le Sprint 1, il faut gérer le cas où 1 aventurier veux aller sur une case où il y a un aventurier.
Pour l'instant il ira sur la case. De plus si les 2 sont sur une case ou un trésor est disponnible, le 1er dans la liste
prendra le trésor .. (injustice !!)
- Il y a encore des parties du code à améliorer (Prochaine étape de refacto)
- Sécuriser les inputs et les données incohérentes

## Etude du _Sprint n°3_
### Idée n°1 
- Dans la suite on parle d'un clic pour parler d'une étape T à un moment précis. 
- Pour le moment chaque action prend 1 clic (marché, tourné, ramasser 1 trésors).
- On pourrai très bien imaginer changer le comportement d'un aventurier pour lui préciser combien de clic prends chaque 
  étape. Ainsi on pourrait avoir des aventuriers qui agissent de manière asynchrone.

#### Idée n°2 
- les `adventurers` et la `map` n'étant pas directement connecté, mais c'est l'`engine` qui gère la relation, on peux 
envisager une autre solution. Chaque `adventurer` va indiquer à l'`engine` ce qu'il veut faire comme action, au moment
où il va le faire. Et à ce moment là, c'est l'`engine` qui décidera si c'est possible ou pas. 
On peut imaginer une archi basé sur des évènements.