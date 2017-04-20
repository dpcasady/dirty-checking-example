package dirty.checking.example

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
class BookSpec extends Specification {

    void "dirty property with default setter is persisted"() {
        given:
        Book b = new Book(title: 'My Book')

        when:
        Book.withTransaction {
            b.save()
            b.title = 'Updated Book'
        }
        Book.withTransaction { b.refresh() }

        then:
        b.title == 'Updated Book'
    }

    void "dirty property with custom setter is not persisted"() {
        given:
        Book b = new Book(author: 'My Author')

        when:
        Book.withTransaction {
            b.save()
            b.author = 'Updated Author'
        }

        then:
        Book.withTransaction { b.refresh() }

        then:
        b.author == 'Updated Author' // fails
    }
}
