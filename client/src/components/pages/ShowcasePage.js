import styled from 'styled-components';
import { useEffect } from 'react';
import { ClipLoader } from 'react-spinners';

import useShowcaseStore from '../../store/showcaseStore';
import ShowcaseTitle from '../molecules/ShowcaseTitle';
import Showcasebox from '../organisms/showcase/Showcasebox';
import ShowcaseModal from '../organisms/showcase/ShowcaseModal';
import useIntersect from '../../hooks/useIntersect';
import useShowcaseModal from '../../store/showcaseModalStore';

const Showcase = () => {
  const { itemList, isLoading, getItemListForTest } = useShowcaseStore();
  const { isModalOpen, getModalItemTest } = useShowcaseModal();

  const ref = useIntersect(async (entry, observer) => {
    // TODO: 너무 빠른 업데이트 요청 방지로직 추가

    observer.unobserve(entry.target);
    await getItemListForTest();
    observer.observe(entry.target);
  });

  const handleModal = async id => {
    await getModalItemTest(id);
  };

  const renderItem = line => {
    return itemList.map((el, idx) => {
      const { id, thumbnailUrl, category, content, writer, commentUserName, commentContent } = el;
      return idx % 3 === line ? (
        <Showcasebox
          key={id}
          thumnail={thumbnailUrl}
          tagName={category}
          summary={content}
          userImg={writer.profileImageUrl}
          userName={writer.nickname}
          commentUserName={commentUserName}
          commentContent={commentContent}
          handle={() => handleModal(id)}
        />
      ) : null;
    });
  };

  useEffect(() => {
    getItemListForTest();
  }, []);
  return (
    <>
      <ShowcaseModal isModalOpen={isModalOpen} />
      <Container>
        <ShowcaseTitle />
        <Body>
          <CaseContainer>{renderItem(0)}</CaseContainer>
          <CaseContainer>{renderItem(1)}</CaseContainer>
          <CaseContainer>{renderItem(2)}</CaseContainer>
        </Body>
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
`;

const Body = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  width: 100%;
  height: auto;
`;

const CaseContainer = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  padding-right: 32px;

  &:last-child {
    padding-right: 0px;
  }
`;

const InfinityScrollSection = styled.div`
  height: 1px;
`;

export default Showcase;
