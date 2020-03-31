package org.occidere.githubnotifier.service;

import java.util.List;
import org.occidere.githubnotifier.vo.GithubRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author occidere
 * @since 2019. 11. 29.
 * Blog: https://blog.naver.com/occidere
 * Github: https://github.com/occidere
 */
@Repository
public interface GithubRepoRepository extends ElasticsearchRepository<GithubRepository, String> {
    List<GithubRepository> findAllByOwnerLogin(String login);
}
