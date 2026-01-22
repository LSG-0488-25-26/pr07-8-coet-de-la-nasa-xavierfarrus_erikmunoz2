# PR06 - Lazy Components (Yu-Gi-Oh cards)

Simplified version adapted to the provided example style.

How it works
- Data provider: `viewmodel/CardProvider.kt` exposes `getCardList()` returning a `MutableList<YuGiOhCard>`. For MVVM/LiveData practice I added `viewmodel/CardListViewModel.kt` which exposes `LiveData<List<YuGiOhCard>>` and is used by the list & detail screens.
- Model: `Model/YuGiOhCard.kt` contains the data class with `index: String` and `@DrawableRes image: Int`.
- Views: `View/CardListScreen.kt` and `View/CardDetailScreen.kt` use `getCardList()` directly (no ViewModel class required) and navigate using the `card.index` as a string route.
- Navigation: `Navigation/Routes.kt` defines `Routes.LIST` and `Routes.DETAIL` (detail uses `cardIndex` as `String`).

Drawables
- I detected these drawable resources in `app/src/main/res/drawable/`:
  - `bond.jpg`, `bongos.jpg`, `caracol.jpg`, `e.jpg`, `esqueleto.jpg`, `fuego.jpg`, `laboratorio.jpg`, `libro.jpg`, `nene.jpg`, `penguin.jpg`, `prisionero.jpg`, `rio.jpg`
- I enriched the card entries by fetching each card's information from the YGOPRODeck API using the IDs in the image URLs and mapped them to local drawables in `viewmodel/CardProvider.kt`:
  - `bond` → id **60709218** → "Bond Between Teacher and Student" → `R.drawable.bond`
  - `penguin` → id **48531733** → "Bolt Penguin" → `R.drawable.penguin`
  - `caracol` → id **12146024** → "Bolt Escargot" → `R.drawable.caracol`
  - `laboratorio` → id **6890729** → "Bonding - DHO" → `R.drawable.laboratorio`
  - `esqueleto` → id **25784595** → "Bone Archfiend" → `R.drawable.esqueleto`
  - `bongos` → id **47778083** → "Bone Temple Block" → `R.drawable.bongos`
  - `fuego` → id **59834564** → "Bonfire Colossus" → `R.drawable.fuego`
  - `nene` → id **263926** → "Bonze Alone" → `R.drawable.nene`
  - `e` → id **24425055** → "Booby Trap E" → `R.drawable.e`
  - `rio` → id **96704974** → "Boogie Trap" → `R.drawable.rio`
  - `libro` → id **35480699** → "Book of Eclipse" → `R.drawable.libro`
  - `prisionero` → id **89558090** → "Dark Prisoner" → `R.drawable.prisionero`
- If you prefer other images or different mappings, tell me which `drawable` name to use for each card and I’ll update `CardProvider.kt` accordingly.

Next steps you can ask me to do
- Update the sample provider to reference your actual drawables (once you add images with those exact names).
- Implement network fetching from `https://db.ygoprodeck.com/api/v7/cardinfo.php`.
- Add CI workflow to build the project on push (useful since your local environment may lack JAVA_HOME).

Notes
- Navigation: to navigate to detail, use `navController.navigate(Routes.detail(card.index))`.
- Detail screen expects a string index: the nav argument type is `NavType.StringType`.
