# This is Test App for Baraka

Libraries  wich I chose for solving task:
- AndroidX ViewModel - for presentation layer I chose MVVM pattern with androidX ViewModel instead of another MV* patterns, becouse view has 
reactive updating of state, we can easealy share VM between another another View and this pattern promotes by Google. We have several 
under the hood features in androiX like viewModelScope for coroutines and creating factories.
- Hilt - at first, I would like to prefer DI framework with compile time building dependency tree, and becouse of this my choice was between Hilt and
Dagger 2, instead of Koin, Toothpick and etc. Hilt is like superstructure on Dagger, which allow developer write less boilerplate code without 
hage Components and with convenient scopes like @HiltViewModel.
- Kotlin Coroutines/Flow - for asynchronous tasks I chose coroutines/flow becouse of this tool allow write easy to understand consistent code. Moreover, 
coroutines allow to write nonblocking threads code which lead to better performance in I/O oeprations, and last but no least coroutines supported by Google
- ViewBinding - this tool just for more comfortable calles to layout items
- Adapter Delegates - for working with RecyclerView without boilerplate code like every time writing adapters and holders. I gues in real project I would
like to prefer inhouse tool for this solution for more flexibility or using LazyColomn from Jetpack Compose, if whole project will use it
- Coil - for loading images instead of Glide/Picasso becouse of this image loader written in kotlin and has compatibility with compose for future, if
project will be rewritten with Compose
