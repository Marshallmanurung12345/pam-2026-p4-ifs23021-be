
package org.delcom.module

import org.delcom.dao.DestinationDAO
import org.delcom.dao.PlantDAO
import org.delcom.repositories.DestinationRepository
import org.delcom.repositories.IDestinationRepository
import org.delcom.repositories.IPlantRepository
import org.delcom.repositories.PlantRepository
import org.delcom.services.DestinationService
import org.delcom.services.PlantService
import org.delcom.services.ProfileService
import org.koin.dsl.module

val appModule = module {

    // ===== DELCOM PLANTS (JANGAN DIHAPUS) =====
    // The DAO classes (PlantDAO, DestinationDAO) are managed by Exposed and
    // should not be instantiated manually. Repositories handle DAO usage.
    single<IPlantRepository> { PlantRepository() }
    single { PlantService(get()) }

    // ===== PROFILE (tetap) =====
    single { ProfileService() }

    // ===== WISATA SAMOSIR (API BARU) =====
    // like plants, do not instantiate DAO directly
    single<IDestinationRepository> { DestinationRepository() }
    single { DestinationService(get()) }
}

