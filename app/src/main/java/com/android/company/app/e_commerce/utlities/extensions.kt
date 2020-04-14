package com.android.company.app.e_commerce.utlities

import com.android.company.app.e_commerce.models.CategoryResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

//import com.google.android.gms.tasks.Task
//import kotlinx.coroutines.CancellationException
//import kotlinx.coroutines.CompletableDeferred
//import kotlinx.coroutines.Deferred
//import kotlinx.coroutines.suspendCancellableCoroutine
//import kotlin.coroutines.resumeWithException
//
///**
// * Converts this task to an instance of [Deferred].
// * If task is cancelled then resulting deferred will be cancelled as well.
// */
//fun <T> Task<T>.asDeferred(): Deferred<T> {
//    if (isComplete) {
//        val e = exception
//        return if (e == null) {
//            @Suppress("UNCHECKED_CAST")
//            CompletableDeferred<T>().apply { if (isCanceled) cancel() else complete(result as T) }
//        } else {
//            CompletableDeferred<T>().apply { completeExceptionally(e) }
//        }
//    }
//    val result = CompletableDeferred<T>()
//    addOnCompleteListener {
//        val e = it.exception
//        if (e == null) {
//            @Suppress("UNCHECKED_CAST")
//            if (isCanceled) result.cancel() else result.complete(it.result as T)
//        } else {
//            result.completeExceptionally(e)
//        }
//    }
//    return result
//}
///**
// * Awaits for completion of the task without blocking a thread.
// *
// * This suspending function is cancellable.
// * If the [Job] of the current coroutine is cancelled or completed while this suspending function is waiting, this function
// * stops waiting for the completion stage and immediately resumes with [CancellationException].
// */
//public suspend fun <T> Task<T>.await(): T {
//    // fast path
//    if (isComplete) {
//        val e = exception
//        return if (e == null) {
//            if (isCanceled) {
//                throw CancellationException("Task $this was cancelled normally.")
//            } else {
//                @Suppress("UNCHECKED_CAST")
//                result as T
//            }
//        } else {
//            throw e
//        }
//    }
//    return suspendCancellableCoroutine { cont ->
//        addOnCompleteListener {
//            val e = exception
//            if (e == null) {
//                @Suppress("UNCHECKED_CAST")
//                if (isCanceled) cont.cancel() else cont.resume(result as T)
//            } else {
//                cont.resumeWithException(e)
//            }
//        }
//    }
//}
//
//suspend fun getUser(id: String) : User? {
//    val snapshot = try {
//        FirebaseFirestore.getInstance().document("user/$id").get().await()
//    } catch (e: Exception) {
//        null
//    }
//    return snapshot?.toObject(User::class.java)
//}

//
//class UserObserver {
//
//    private val scope = CoroutineScope(Dispatchers.IO)
//    private var firebaseDatabase = FirebaseDatabase.getInstance()   // 1
//
//    @ExperimentalCoroutinesApi
//    private val flow = callbackFlow<CategoryResponse> {   // 2
//        val databaseReference = firebaseDatabase.getReference("users")    // 3
//        val eventListener = databaseReference.addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//        })
//        awaitClose {   // 6
//            databaseReference.removeEventListener(eventListener)
//        }
//    }
////
////    fun observe() {
////        scope.launch {
////            flow.collect { category ->   // 7
////                category.name
////            }
////        }
////    }
//}