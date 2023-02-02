import styled from 'styled-components';
import { useEffect } from 'react';
import { ClipLoader } from 'react-spinners';
import { Masonry } from 'masonic';

import useShowcaseStore from '../../store/showcaseStore';
import ShowcaseTitle from '../molecules/showcase/ShowcaseTitle';
import Showcasebox from '../organisms/showcase/Showcasebox';
import ShowcaseModal from '../organisms/showcase/ShowcaseModal';
import useIntersect from '../../hooks/useIntersect';
import useShowcaseModal from '../../store/showcaseModalStore';

const Showcase = () => {
  const { itemList, isLoading, getItemList, initializeStore } = useShowcaseStore();
  const { isModalOpen, getModalItem } = useShowcaseModal();

  useEffect(() => {
    getItemList(9);
    return () => {
      initializeStore();
    };
  }, []);

  const ref = useIntersect(async (entry, observer) => {
    await getItemList(9, () => {
      observer.unobserve(entry.target);
    });
  });

  const handleModal = async id => {
    await getModalItem(id);
  };

  const renderMasonicCard = ({ data: { id, thumbnailUrl, category, content, writer, lastComment } }) => {
    return (
      <Showcasebox
        key={id}
        id={id}
        thumnail={thumbnailUrl}
        tagName={category}
        summary={content}
        userId={writer.id}
        userImg={writer.profileUrl}
        userName={writer.nickname}
        commentUserName={lastComment ? lastComment.writer.nickname : null}
        commentContent={lastComment ? lastComment.content : null}
        handle={() => handleModal(id)}
      />
    );
  };

  return (
    <>
      {isModalOpen ? <ShowcaseModal isModalOpen={isModalOpen} /> : null}
      <Container>
        <ShowcaseTitle />
        <Masonry items={itemList} columnGutter={32} columnWidth={330} overscanBy={9} render={renderMasonicCard} />
        {isLoading ? <ClipLoader /> : null}
        <InfinityScrollSection ref={ref} />
      </Container>
    </>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: auto;
  gap: 20px;
`;

const InfinityScrollSection = styled.div`
  height: 1px;
`;

export default Showcase;
