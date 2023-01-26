import styled from 'styled-components';
import { useRef, useEffect } from 'react';
import Box from '../../atoms/Box';
import useShowcaseModal from '../../../store/showcaseModalStore';
import { UserInfoSmall } from '../../molecules/UserInfo';
import { ParagraphMedium } from '../../../styles/typo';
import Category from '../../atoms/Category';
import Comments from '../Comments';

const ShowcaseModal = ({ isModalOpen }) => {
  const modalRef = useRef(null);
  const { modalItem, toggleModalOpen } = useShowcaseModal();

  const handleClickOutside = e => {
    const condition = isModalOpen && !modalRef.current.contains(e.target);
    if (condition) {
      toggleModalOpen();
    }
  };

  useEffect(() => {
    window.addEventListener('mousedown', handleClickOutside);

    return () => {
      window.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  return (
    <Container isModalOpen={isModalOpen}>
      <Body ref={modalRef}>
        <TopContainer>
          <ImageBox padding="0px">
            <Image src={modalItem.images[0].fileURL} />
          </ImageBox>
          <ShowcaseContents>
            <Box>
              <UserInfoSmall
                id={modalItem.writer.id}
                name={modalItem.writer.nickname}
                image={modalItem.writer.profileImageUrl}
              />
            </Box>
            <Box margin="15px 0px 35px 0px">
              <Content>{modalItem.content}</Content>
            </Box>
            <Category padding="20px" color="rgba(51, 51, 51, 1)">
              {modalItem.category}
            </Category>
          </ShowcaseContents>
        </TopContainer>
        <CommentListContainer>
          <Comments comments={modalItem.comments} />
        </CommentListContainer>
      </Body>
    </Container>
  );
};

export default ShowcaseModal;

const Container = styled.div`
  display: ${props => (props.isModalOpen ? 'flex' : 'none')};
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  background-color: rgba(0, 0, 0, 0.5);
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  width: 1057px;
  height: 1000px;
  z-index: 99999;
  gap: 32px;
`;

const TopContainer = styled.div`
  display: flex;
  height: 632px;
  gap: 32px;
`;

const ImageBox = styled(Box)`
  max-width: 693px;
  margin-right: 32px;
`;

const Image = styled.img`
  height: 100%;
`;

const Content = styled.div`
  ${ParagraphMedium};
  height: 438px;
`;

const ShowcaseContents = styled.div`
  width: 331px;
  display: flex;
  flex-direction: column;
`;

const CommentListContainer = styled.div`
  width: 100%;
  background-color: white;
`;
